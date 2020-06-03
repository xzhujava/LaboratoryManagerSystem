package com.laboratory.entity;

import java.io.Serializable;

/**
 * @Author 张栓
 * @Date 2020/6/3 17:49
 * @Version 1.0
 */
public class Laboratory implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 实验室id
     */
    private Integer laboratoryId;


    private String laboratoryName;

    private String laboratoryLocation;

    private Integer status;
}
