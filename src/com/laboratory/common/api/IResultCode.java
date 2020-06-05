package com.laboratory.common.api;

import java.io.Serializable;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/5 9:43
 */
public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}
