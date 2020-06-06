package com.laboratory.vo;

import com.laboratory.entity.Reservation;

import java.io.Serializable;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 13:15
 */
public class ReservationVO extends Reservation implements Serializable {
    private static final long serialVersionUID=1L;

    private String laboratoryName;

    private String userNo;

    private String userName;

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
