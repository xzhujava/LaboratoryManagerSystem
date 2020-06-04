package com.laboratory.common.enumerate;

/**
 * 实验室是否可预约状态
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 13:15
 */
public enum LaboratoryStatus {
    RESETVED(0),
    UNRESETVED(1);
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    LaboratoryStatus(Integer value){
        this.value = value;
    }
}
