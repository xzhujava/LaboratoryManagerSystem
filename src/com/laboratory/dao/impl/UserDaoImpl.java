package com.laboratory.dao.impl;

import com.laboratory.common.utils.DBHelper;
import com.laboratory.dao.UserDao;
import com.laboratory.entity.Menu;
import com.laboratory.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 18:35
 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findStudent(String sql) throws Exception {
        List<User> studentList = null;
        Connection connection = DBHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        studentList = new ArrayList<User>();
        while (resultSet.next()){
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(resultSet.getString("user_name"));
            user.setUserNo(resultSet.getString("user_no"));
            user.setUserPassword(resultSet.getString("user_password"));
            user.setUserSex(resultSet.getString("user_sex"));
            user.setRemark(resultSet.getString("remark"));
            studentList.add(user);
        }
        DBHelper.release(preparedStatement,resultSet);
        return studentList;
    }
}
