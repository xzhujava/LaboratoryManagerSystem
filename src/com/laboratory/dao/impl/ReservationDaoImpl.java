package com.laboratory.dao.impl;

import com.laboratory.common.utils.DBHelper;
import com.laboratory.dao.ReservationDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/5 21:42
 */
public class ReservationDaoImpl implements ReservationDao {

    @Override
    public String getId(String sql) throws Exception {
        String id = null;
        Connection connection = DBHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            id = resultSet.getString("user_id");
        }
        return id;
    }
}
