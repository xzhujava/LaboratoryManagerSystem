package com.laboratory.dao;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/5 21:41
 */
public interface ReservationDao {

    /**
     * 获取主键id值
     * @param sql
     * @return
     * @throws Exception
     */
    String getId(String sql) throws Exception;
}
