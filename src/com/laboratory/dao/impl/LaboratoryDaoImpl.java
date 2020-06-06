package com.laboratory.dao.impl;

import com.laboratory.common.utils.DBHelper;
import com.laboratory.dao.LaboratoryDao;
import com.laboratory.entity.Laboratory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 14:33
 */
public class LaboratoryDaoImpl implements LaboratoryDao {
    @Override
    public List<Laboratory> findAllName() throws Exception{
        List<Laboratory> laboratoryList = null ;
        String sql = "select laboratory_id as laboratoryId,laboratory_name as laboratoryName from laboratory";
        Connection connection = DBHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        laboratoryList = new ArrayList<Laboratory>();
        while (resultSet.next()){
            Laboratory laboratory = new Laboratory();
            laboratory.setLaboratoryId(resultSet.getString("laboratoryId"));
            laboratory.setLaboratoryName(resultSet.getString("laboratoryName"));
            laboratoryList.add(laboratory);
        }
        return laboratoryList;
    }
}
