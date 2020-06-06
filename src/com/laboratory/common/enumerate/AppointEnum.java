package com.laboratory.common.enumerate;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 17:00
 */
public enum  AppointEnum {
    FAILBOOK("预约失败"),
    BOOKED("实验室已被预定");

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    AppointEnum(String message) {
        this.message = message;
    }
}
