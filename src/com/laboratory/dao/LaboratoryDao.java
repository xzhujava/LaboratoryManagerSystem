package com.laboratory.dao;

import com.laboratory.entity.Laboratory;

import java.util.List;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 14:32
 */
public interface LaboratoryDao {
    /**
     * 获取所有实验室名称
     * @return
     * @throws Exception
     */
    List<Laboratory> findAllName() throws Exception;
}
