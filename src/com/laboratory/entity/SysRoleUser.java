package com.laboratory.entity;

import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * 角色和用户关联信息
 * @author 张栓
 * @date 2020/6/3 17:47
 * @version 1.0
 */
@Entity("sys_role_user")
public class SysRoleUser implements Serializable {
    private static final long serialVersionUID=1L;

    public SysRoleUser() {
    }

    public SysRoleUser(Integer userId) {
        //永远新增学生
        this.roleId = 1;
        this.userId = userId;
    }

    /**
     * 角色id
     */
    @Id("role_id")
    private Integer roleId;

    /**
     * 用户id
     */
    @Id("user_id")
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
