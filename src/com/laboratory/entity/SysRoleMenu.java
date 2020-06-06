package com.laboratory.entity;

import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:23
 */
@Entity("sys_role_menu")
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 菜单id
     */
    @Id("menu_id")
    private String menuId;

    /**
     * 角色id
     */
    @Id("role_id")
    private String roleId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
