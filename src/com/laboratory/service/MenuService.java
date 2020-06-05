package com.laboratory.service;

import com.laboratory.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:34
 */
public interface MenuService {
    /**
     * 根据用户id查询菜单
     * @param map
     * @return
     */
    List<Menu> findMenu(Map<String,Object> map) throws Exception;
}
