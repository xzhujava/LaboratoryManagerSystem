package com.laboratory.entity;

import com.laboratory.common.annotation.Column;
import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * 用户信息
 * @author 张栓
 * @date 2020/6/3 17:45
 * @version 1.0
 */
@Entity("user")
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @Id("user_id")
    private Integer userId;

    /**
     * 用户姓名
     */
    @Column("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column("user_password")
    private String userPassword;

    /**
     * 用户性别
     */
    @Column("user_sex")
    private String userSex;

    /**
     * 备注
     */
    @Column("remark")
    private String remark;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex='" + userSex + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
