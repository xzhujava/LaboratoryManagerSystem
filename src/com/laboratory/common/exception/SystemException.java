package com.laboratory.common.exception;

/**
 * 系统全局异常
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/3 21:28
 */
public class SystemException extends Exception{
    private static final long serialVersionUID = 1639374111871115063L;

    public SystemException(String message){
        super(message);
    }
}
