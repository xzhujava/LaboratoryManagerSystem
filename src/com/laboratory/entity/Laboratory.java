package com.laboratory.entity;

import com.laboratory.common.annotation.Column;
import com.laboratory.common.annotation.Entity;
import com.laboratory.common.annotation.Id;

import java.io.Serializable;

/**
 * 实验室信息
 * @author 张栓
 * @date  2020/6/3 17:49
 * @version 1.0
 */
@Entity("laboratory")
public class Laboratory implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 实验室id
     */
    @Id("laboratory_id")
    private String laboratoryId;

    /**
     * 实验室名称
     */
    @Column("laboratory_name")
    private String laboratoryName;

    /**
     * 实验室地址
     */
    @Column("laboratory_location")
    private String laboratoryLocation;

    /**
     * 实验室状态 0：可预约 1：不可预约
     */
    @Column("status")
    private Integer status;

    public String getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryLocation() {
        return laboratoryLocation;
    }

    public void setLaboratoryLocation(String laboratoryLocation) {
        this.laboratoryLocation = laboratoryLocation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "laboratoryId=" + laboratoryId +
                ", laboratoryName='" + laboratoryName + '\'' +
                ", laboratoryLocation='" + laboratoryLocation + '\'' +
                ", status=" + status +
                '}';
    }
}
