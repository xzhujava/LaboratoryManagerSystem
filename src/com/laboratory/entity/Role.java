package com.laboratory.entity;

import com.laboratory.common.annotation.Column;
import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * 角色信息
 * @author 张栓
 * @date 2020/6/3 17:43
 * @version 1.0
 */
@Entity("role")
public class Role implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 角色id
     */
    @Id("role_id")
    private Integer roleId;
    /**
     *角色名称
     */
    @Column("role_name")
    private String roleName;
    /**
     *角色权限字符串
     */
    @Column("role_key")
    private String roleKey;
    /**
     *备注
     */
    @Column("remark")
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
