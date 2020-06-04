package com.laboratory.service.impl;

import com.laboratory.common.enumerate.UserTypeEnum;
import com.laboratory.common.utils.Md5Utils;
import com.laboratory.dao.GenericDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.entity.User;
import com.laboratory.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 * @author 张栓
 * @date 2020/6/4 9:16
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    /**
     * 引入dao层
     */
    private GenericDao<User> userGenericDao = new GenericDaoImpl<User>();

    /**
     * 查询条件 key：字段 value：字段值
     */
    private Map<String,Object> sqlWhereMap= null;

    @Override
    public User authentication(User loginParam) throws Exception{
        sqlWhereMap = new HashMap<>(16);
        sqlWhereMap.put("user_no",loginParam.getUserNo());
        List<User> users = userGenericDao.findAllByConditions(sqlWhereMap, User.class);
        if(null == users && users.size()<1){
            return null;
        }
        for (User user : users) {
            String hash = Md5Utils.hash(loginParam.getUserPassword());
            if(user.getUserNo().equals(loginParam.getUserNo()) && hash.equals(user.getUserPassword())){
                //用户名和密码匹配成功，把用户类型返回
                return user;
            }
        }
        return null;
    }

    @Override
    public User registered(User userParam) throws Exception{
        userParam.setUserPassword(Md5Utils.hash(userParam.getUserPassword()));
        Integer id = userGenericDao.save(userParam);
        if(id>0){
            userParam.setUserId(id);
            return userParam;
        }
        return null;
    }

    @Override
    public List<User> findStudent(Map<String,Object> requestParam) throws Exception{
        requestParam.put("user_type", UserTypeEnum.STUDENT.getValue());
        return userGenericDao.findAllByConditions(requestParam, User.class);
    }
}
