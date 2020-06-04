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
    public User(){
    }

    public User(String userNo, String userPassword) {
        this.userNo = userNo;
        this.userPassword = userPassword;
    }

    public User(String userName, String userNo, String userPassword, String userSex, Integer userType, String remark) {
        this.userName = userName;
        this.userNo = userNo;
        this.userPassword = userPassword;
        this.userSex = userSex;
        this.userType = userType;
        this.remark = remark;
    }

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
     * 学号或工号
     */
    @Column("user_no")
    private String userNo;

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
     * 用户类型：0管理员 1教师 2学生
     */
    @Column("user_type")
    private Integer userType;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userType=" + userType +
                ", remark='" + remark + '\'' +
                '}';
    }
}
