package com.laboratory.service;

import com.laboratory.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 * @author 张栓
 * @date 2020/6/4 9:05
 * @version 1.0
 */
public interface UserService {

    /**
     * 系统认证，用于用户登录操作
     * 用户验证通过，返回用户全部信息
     * @param loginParam 用户登录信息
     * @return
     */
    User authentication(User loginParam) throws Exception;

    /**
     * 学生注册
     * 注册成功后，返回改学生全部信息包括id
     * @param userParam 注册学生信息
     * @return
     */
    User registered(User userParam) throws Exception;

    /**
     * 查询学生信息
     * @return
     */
    List<User> findStudent(Map<String,Object> requestParam) throws Exception;
}
