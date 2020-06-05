package com.laboratory.service.impl;

import com.laboratory.common.enumerate.UserTypeEnum;
import com.laboratory.common.utils.Md5Utils;
import com.laboratory.dao.GenericDao;
import com.laboratory.dao.UserDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.dao.impl.UserDaoImpl;
import com.laboratory.entity.SysRoleUser;
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
    private GenericDao<SysRoleUser> sysRoleUserGenericDao = new GenericDaoImpl<SysRoleUser>();
    private UserDao userDao = new UserDaoImpl();
    private Map<String,Object> map = null;
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
            //注册成功，把新增的学生信息与角色关联
            SysRoleUser sysRoleUser = new SysRoleUser(id);
            sysRoleUserGenericDao.save(sysRoleUser);
            userParam.setUserId(id);
            return userParam;
        }
        return null;
    }

    @Override
    public List<User> findStudent(Map<String,Object> requestParam) throws Exception{
        //先去角色表中查询
        map = new HashMap<>();
        map.put("role_id",UserTypeEnum.STUDENT.getValue());
        List<SysRoleUser> allStudentType = sysRoleUserGenericDao.findAllByConditions(map, SysRoleUser.class);
        StringBuilder sql = new StringBuilder("select * from user where user_id in (");
        for (SysRoleUser sysRoleUser : allStudentType) {
            sql.append(sysRoleUser.getUserId() + ",");
        }
        sql = new StringBuilder(sql.substring(0,sql.length()-1));
        sql.append(")");
        return userDao.findStudent(sql.toString());
    }
}
