package com.laboratory.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 生成唯一主键工具类
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/5 20:53
 */
public class IdGenUtil {
    private static SecureRandom random = new SecureRandom();
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
}
