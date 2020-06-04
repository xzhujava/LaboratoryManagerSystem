package com.laboratory.common.utils;

import com.laboratory.common.exception.SystemException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Md5加密方法
 * @author : 张栓
 * @date : 2020/6/4 9:36
 */
public class Md5Utils {
    private Md5Utils() {
    }

    private static byte[] md5(String s) throws SystemException{
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            throw new SystemException("MD5 exception");
        }
    }

    private static final String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) throws Exception{
        try {
            return new String(toHex(md5(s)).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SystemException("hashException");
        }
    }
}
