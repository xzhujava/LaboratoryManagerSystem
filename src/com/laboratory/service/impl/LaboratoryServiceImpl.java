package com.laboratory.service.impl;

import com.laboratory.dao.GenericDao;
import com.laboratory.dao.LaboratoryDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.dao.impl.LaboratoryDaoImpl;
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
    private LaboratoryDao laboratoryDao = new LaboratoryDaoImpl();

    @Override
    public List<Laboratory> getLaboratoryInfo(Map<String, Object> requestParam) throws Exception{
        return laboratoryGenericDao.findAllByConditions(requestParam,Laboratory.class);
    }

    @Override
    public List<Laboratory> getAllName() throws Exception {
        return laboratoryDao.findAllName();
    }

    @Override
    public Boolean changeStatus(String laboratoryId) throws Exception {
        Laboratory laboratory = laboratoryGenericDao.get(laboratoryId, Laboratory.class);
        laboratory.setStatus(0);
        Integer update = laboratoryGenericDao.update(laboratory);
        if(update>0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
