package com.laboratory.service.impl;

import com.laboratory.dao.GenericDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.entity.Laboratory;
import com.laboratory.service.LaboratoryService;

import java.util.List;
import java.util.Map;

/**
 * 实验室服务实现类
 * @author 张栓
 * @date 2020/6/4 10:01
 * @version 1.0
 */
public class LaboratoryServiceImpl implements LaboratoryService {

    private GenericDao<Laboratory> laboratoryGenericDao = new GenericDaoImpl<Laboratory>();

    @Override
    public List<Laboratory> getLaboratoryInfo(Map<String, Object> requestParam) throws Exception{
        return laboratoryGenericDao.findAllByConditions(requestParam,Laboratory.class);
    }
}
