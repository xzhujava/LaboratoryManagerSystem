package com.laboratory.service.impl;

import com.laboratory.dao.GenericDao;
import com.laboratory.dao.MenuDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.dao.impl.MenuDaoImpl;
import com.laboratory.entity.Menu;
import com.laboratory.entity.SysRoleMenu;
import com.laboratory.entity.SysRoleUser;
import com.laboratory.service.MenuService;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:35
 */
public class MenuServiceImpl implements MenuService {

    private MenuDao menuDao = new MenuDaoImpl();
    private GenericDao<SysRoleMenu> sysRoleMenuGenericDao = new GenericDaoImpl<SysRoleMenu>();
    private GenericDao<SysRoleUser> sysRoleUserGenericDao = new GenericDaoImpl<SysRoleUser>();
    private Map<String,Object> param = null;
    private List<SysRoleMenu> roleMenus = null;
    private List<Menu> menuList = null;

    @Override
    public List<Menu> findMenu(Map<String, Object> map) throws Exception{
        List<SysRoleUser> roleUsers = sysRoleUserGenericDao.findAllByConditions(map, SysRoleUser.class);
        if(null != roleUsers && roleUsers.size()>0){
            param = new HashMap<>();
            roleMenus = new ArrayList<SysRoleMenu>();
            SysRoleUser sysRoleUser = roleUsers.get(0);
            String roleId = sysRoleUser.getRoleId();
            param.put("role_id",roleId);
            roleMenus = sysRoleMenuGenericDao.findAllByConditions(param, SysRoleMenu.class);
        }
        if(null != roleMenus && roleMenus.size()>0){
            param = new HashMap<>();
            menuList = new ArrayList<Menu>();
            StringBuilder sql = new StringBuilder("select * from menu where menu_id in (");
            for (SysRoleMenu roleMenu : roleMenus) {
                sql.append(roleMenu.getMenuId()+ ",");
            }
            sql = new StringBuilder(sql.substring(0,sql.length()-1));
            sql.append(")");
            menuList = menuDao.findMenu(sql.toString());

        }
        return menuList;
    }
}
