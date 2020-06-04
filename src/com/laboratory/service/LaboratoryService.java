package com.laboratory.service;

import com.laboratory.entity.Laboratory;

import java.util.List;
import java.util.Map;

/**
 * 实验室服务接口
 * @author 张栓
 * @date 2020/6/4 9:56
 * @version 1.0
 */
public interface LaboratoryService {
    /**
     * 条件查询实验室信息
     * @return
     */
    List<Laboratory> getLaboratoryInfo(Map<String,Object> requestParam) throws Exception;

}
