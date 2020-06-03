package com.laboratory.entity;

import java.io.Serializable;

/**
 * @Author 张栓
 * @Date 2020/6/3 17:47
 * @Version 1.0
 */
public class SysRoleUser implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户id
     */
    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysRoleUser{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
