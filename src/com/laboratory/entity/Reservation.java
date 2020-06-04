package com.laboratory.entity;

import com.laboratory.common.annotation.Column;
import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实验室预约记录
 * @author 张栓
 * @date 2020/6/3 20:30
 */
@Entity("reservation")
public class Reservation implements Serializable {
    private static final long serialVersionUID=1L;

    public Reservation() {
    }

    //新增预约记录
    public Reservation(Integer laboratoryId, Integer userId, LocalDateTime reservationTime, LocalDateTime createTime, String remark) {
        this.laboratoryId = laboratoryId;
        this.userId = userId;
        this.reservationTime = reservationTime;
        this.createTime = createTime;
        this.remark = remark;
        //新增时默认为0
        this.status = 0;
    }

    /**
     *预约id
     */
    @Id("reservation_id")
    private Integer reservationId;

    /**
     * 实验室id
     */
    @Column("laboratory_id")
    private Integer laboratoryId;

    /**
     * 用户id
     */
    @Column("user_id")
    private Integer userId;

    /**
     * 预约时间
     */
    @Column("reservation_time")
    private LocalDateTime reservationTime;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @Column("remark")
    private String remark;

    /**
     * 预约状态 0：已预约 1：已完成使用
     */
    @Column("status")
    private Integer status;

    /**
     * 实验成绩
     */
    @Column("score")
    private Double score;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(Integer laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", laboratoryId=" + laboratoryId +
                ", userId=" + userId +
                ", reservationTime=" + reservationTime +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", score=" + score +
                '}';
    }
}
