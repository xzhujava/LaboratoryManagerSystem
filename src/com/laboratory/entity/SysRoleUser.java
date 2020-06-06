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

    public SysRoleUser(String userId) {
        //永远新增学生,
        this.roleId = "1";
        this.userId = userId;
    }

    /**
     * 角色id
     */
    @Id("role_id")
    private String roleId;

    /**
     * 用户id
     */
    @Id("user_id")
    private String userId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
