package com.laboratory.dao;

import java.util.List;
import java.util.Map;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/3 21:03
 */
public interface GenericDao<T> {

    void save(T t) throws Exception;

    void delete(Object id,Class<T> clazz) throws Exception;

    void update(T t) throws Exception;

    T get(Object id,Class<T> clazz) throws Exception;

    /**
     * 根据条件查询
     * @param sqlWhereMap key：条件字段名 value：条件字段值
     * @param clazz
     * @return
     * @throws Exception
     */
    List<T> findAllByConditions(Map<String,Object> sqlWhereMap, Class<T> clazz) throws Exception;

}
