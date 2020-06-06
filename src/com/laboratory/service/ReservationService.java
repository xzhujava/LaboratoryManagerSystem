package com.laboratory.service;

import com.laboratory.entity.Reservation;
import com.laboratory.vo.ReservationVO;

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
    String appointLaboratory(Reservation reservation) throws Exception;

    /**
     * 获取预约信息
     * @param requestParam
     * @return
     */
    List<ReservationVO> getAppointInfos(Map<String,Object> requestParam) throws Exception;

    /**
     * 修改预约记录状态
     * @param reservationId
     * @return
     */
    Boolean changeStatus(String reservationId) throws Exception;
}
