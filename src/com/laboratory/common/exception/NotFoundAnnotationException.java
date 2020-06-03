package com.laboratory.common.exception;

/**
 * 找不到注释异常
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/3 21:16
 */
public class NotFoundAnnotationException extends Exception {
    private static final long serialVersionUID = -994962710559017255L;

    public NotFoundAnnotationException(String message){
        super(message);
    }
}
