package com.laboratory.dao.impl;

import com.laboratory.common.utils.DBHelper;
import com.laboratory.dao.MenuDao;
import com.laboratory.entity.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:47
 */
public class MenuDaoImpl implements MenuDao {

    @Override
    public List<Menu> findMenu(String sql) throws Exception{
        List<Menu> menuList = null;
        Connection connection = DBHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        menuList = new ArrayList<Menu>();
        while (resultSet.next()){
            Menu menu = new Menu();
            menu.setMenuId(resultSet.getString("menu_id"));
            menu.setMenuName(resultSet.getString("menu_name"));
            menu.setMenuKey(resultSet.getString("menu_key"));
            menuList.add(menu);
        }
        DBHelper.release(preparedStatement,resultSet);
        return menuList;
    }
}
