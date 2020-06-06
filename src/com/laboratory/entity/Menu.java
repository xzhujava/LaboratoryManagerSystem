package com.laboratory.entity;

import com.laboratory.common.annotation.Column;
import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * 菜单表
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 15:19
 */
@Entity("menu")
public class Menu implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 菜单id
     */
    @Id("menu_id")
    private String menuId;

    /**
     * 菜单名称
     */
    @Column("menu_name")
    private String menuName;

    @Column("menu_key")
    private String menuKey;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", menuKey='" + menuKey + '\'' +
                '}';
    }
}
