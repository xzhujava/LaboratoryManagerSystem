package com.laboratory.service.impl;

import com.laboratory.common.enumerate.LaboratoryStatus;
import com.laboratory.dao.GenericDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.entity.Laboratory;
import com.laboratory.entity.Reservation;
import com.laboratory.service.ReservationService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约信息服务实现类
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 10:05
 */
public class ReservationServiceImpl implements ReservationService {

    private GenericDao<Reservation> reservationGenericDao = new GenericDaoImpl<Reservation>();
    private GenericDao<Laboratory> laboratoryGenericDao = new GenericDaoImpl<Laboratory>();
    private Map<String,Object> map = null;

    @Override
    public Integer appointLaboratory(Reservation reservation) throws Exception{
        //先去查询实验室是否被预定
        Integer laboratoryId = reservation.getLaboratoryId();
        Laboratory laboratory = laboratoryGenericDao.get(laboratoryId, Laboratory.class);
        Integer status = laboratory.getStatus();
        if(status.equals(LaboratoryStatus.RESETVED.getValue())){
            Integer id = reservationGenericDao.save(reservation);
            if(id > 0){
                // 预定成功修改实验室状态
                laboratory.setStatus(LaboratoryStatus.UNRESETVED.getValue());
                laboratoryGenericDao.update(laboratory);
                return id;
            }
        }else{
            //被预定的实验时间是否在本次试验之后,如果在本次预定时间之前则不可预定
            map = new HashMap<>();
            map.put("laboratory_id",laboratoryId);
            List<Reservation> allReservation = reservationGenericDao.findAllByConditions(map, Reservation.class);
            for (Reservation reservation1 : allReservation) {
                LocalDateTime reservationTime = reservation1.getReservationTime();
                if(!reservationTime.isBefore(reservation.getReservationTime())){
                    return 0;
                }
            }
            //本次预定时间在已经预定的时间之后则可继续预定
            Integer id = reservationGenericDao.save(reservation);
            if(id > 0){
                return id;
            }
            return 0;
        }
        return -1;
    }

    @Override
    public List<Reservation> getAppointInfos(Map<String, Object> requestParam) throws Exception{
        //返回数据优化
        return reservationGenericDao.findAllByConditions(requestParam, Reservation.class);
    }
}
