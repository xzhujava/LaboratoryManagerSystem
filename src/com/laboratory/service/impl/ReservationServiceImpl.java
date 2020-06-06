package com.laboratory.service.impl;

import com.laboratory.common.enumerate.AppointEnum;
import com.laboratory.common.enumerate.LaboratoryStatus;
import com.laboratory.common.utils.DateTimeUtil;
import com.laboratory.dao.GenericDao;
import com.laboratory.dao.ReservationDao;
import com.laboratory.dao.impl.GenericDaoImpl;
import com.laboratory.dao.impl.ReservationDaoImpl;
import com.laboratory.entity.Laboratory;
import com.laboratory.entity.Reservation;
import com.laboratory.entity.User;
import com.laboratory.service.ReservationService;
import com.laboratory.vo.ReservationVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private GenericDao<User> userGenericDao = new GenericDaoImpl<User>();
    private ReservationDao reservationDao = new ReservationDaoImpl();
    private Map<String,Object> map = null;

    @Override
    public String appointLaboratory(Reservation reservation) throws Exception{
        //先去查询实验室是否被预定
        String laboratoryId = reservation.getLaboratoryId();
        Laboratory laboratory = laboratoryGenericDao.get(laboratoryId, Laboratory.class);
        Integer status = laboratory.getStatus();
        String sql = "select * from reservation where laboratory_id = '"+reservation.getLaboratoryId() + "' and user_id = '" + reservation.getUserId() + "' and reservation_time = '" + DateTimeUtil.parseLocalDateTimeToString(reservation.getReservationTime())+"'";
        if(status.equals(LaboratoryStatus.RESETVED.getValue())){
            Integer count = reservationGenericDao.save(reservation);
            if(count > 0){
                // 预定成功修改实验室状态
                laboratory.setStatus(LaboratoryStatus.UNRESETVED.getValue());
                laboratoryGenericDao.update(laboratory);
                return reservationDao.getId(sql);
            }
        }else{
            //被预定的实验时间是否在本次试验之后,如果在本次预定时间之前则不可预定
            map = new HashMap<>();
            map.put("laboratory_id",laboratoryId);
            List<Reservation> allReservation = reservationGenericDao.findAllByConditions(map, Reservation.class);
            for (Reservation reservation1 : allReservation) {
                LocalDateTime reservationTime = reservation1.getReservationTime();
                if(!reservationTime.isBefore(reservation.getReservationTime())){
                    return AppointEnum.BOOKED.getMessage();
                }
            }
            //本次预定时间在已经预定的时间之后则可继续预定
            Integer count = reservationGenericDao.save(reservation);
            if(count > 0){
                return reservationDao.getId(sql);
            }
            return AppointEnum.BOOKED.getMessage();
        }
        return AppointEnum.FAILBOOK.getMessage();
    }

    @Override
    public List<ReservationVO> getAppointInfos(Map<String, Object> requestParam) throws Exception{
        List<ReservationVO> reservationVOList = new ArrayList<>();
        //返回数据优化
        List<Reservation> allReservation = reservationGenericDao.findAllByConditions(requestParam, Reservation.class);
        for (Reservation reservation : allReservation) {
            ReservationVO reservationVO = new ReservationVO();
            reservationVO.setReservationId(reservation.getReservationId());
            reservationVO.setLaboratoryId(reservation.getLaboratoryId());
            reservationVO.setUserId(reservation.getUserId());
            reservationVO.setReservationTime(reservation.getReservationTime());
            reservationVO.setScore(reservation.getScore());
            reservationVO.setCreateTime(reservation.getCreateTime());
            reservationVO.setRemark(reservation.getRemark());
            reservationVO.setStatus(reservation.getStatus());
            User user = userGenericDao.get(reservation.getUserId(), User.class);
            reservationVO.setUserNo(user.getUserNo());
            reservationVO.setUserName(user.getUserName());
            Laboratory laboratory = laboratoryGenericDao.get(reservation.getLaboratoryId(), Laboratory.class);
            reservationVO.setLaboratoryName(laboratory.getLaboratoryName());
            reservationVOList.add(reservationVO);
        }
        return reservationVOList;
    }

    @Override
    public Boolean changeStatus(String reservationId) throws Exception{
        Reservation reservation = reservationGenericDao.get(reservationId, Reservation.class);
        reservation.setStatus(1);
        Integer update = reservationGenericDao.update(reservation);
        if(update>0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
