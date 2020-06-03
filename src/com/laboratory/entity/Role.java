package com.laboratory.entity;

import java.io.Serializable;

/**
 * @Author 张栓
 * @Date 2020/6/3 17:43
 * @Version 1.0
 */
public class Role implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     *角色名称
     */
    private String roleName;
    /**
     *角色权限字符串
     */
    private String roleKey;
    /**
     *备注
     */
    private String remark;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
