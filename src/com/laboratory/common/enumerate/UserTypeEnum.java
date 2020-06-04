package com.laboratory.common.enumerate;

/**
 * 用户类型枚举类
 * @Author 张栓
 * @Date 2020/6/4 9:49
 * @Version 1.0
 */
public enum UserTypeEnum {
    ADMIN(1),
    TEACHER(2),
    STUDENT(3),
    NULL(-1);


    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    UserTypeEnum(Integer value){
        this.value = value;
    }
}
