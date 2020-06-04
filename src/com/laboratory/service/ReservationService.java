package com.laboratory.service;

import com.laboratory.entity.Reservation;

import java.util.List;
import java.util.Map;

/**
 * 预约信息服务接口
 * @author 张栓
 * @date 2020/6/4 10:03
 * @version 1.0
 */
public interface ReservationService {

    /**
     * 预约实验室
     * @param reservation 预约信息
     * @return
     */
    Integer appointLaboratory(Reservation reservation) throws Exception;

    /**
     * 获取预约信息
     * @param requestParam
     * @return
     */
    List<Reservation> getAppointInfos(Map<String,Object> requestParam) throws Exception;

}
