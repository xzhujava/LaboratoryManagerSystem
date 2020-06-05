package com.laboratory.dao;

import com.laboratory.entity.Menu;

import java.sql.Array;
import java.util.List;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:47
 */
public interface MenuDao {

    /**
     * 根据菜单id查询指定菜单集合
     * @param sql
     * @return
     */
    List<Menu> findMenu(String sql) throws Exception;
}
