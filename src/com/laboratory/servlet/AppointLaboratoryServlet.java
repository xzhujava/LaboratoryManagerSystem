package com.laboratory.servlet;

import com.laboratory.common.utils.DateTimeUtil;
import com.laboratory.entity.Reservation;
import com.laboratory.entity.User;
import com.laboratory.service.ReservationService;
import com.laboratory.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * 预约实验室
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 11:47
 */
public class AppointLaboratoryServlet extends HttpServlet {

    private ReservationService reservationService = new ReservationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        try {
            User user = (User) session.getAttribute("user");
            Integer laboratoryId = Integer.parseInt(request.getParameter("laboratoryId"));
            LocalDateTime reservationTime = DateTimeUtil.parseStringToLocalDateTime(request.getParameter("reservationTime"));
            String remark = request.getParameter("remark");
            Reservation reservation = new Reservation(laboratoryId,user.getUserId(),reservationTime,LocalDateTime.now(),remark);
            Integer id = reservationService.appointLaboratory(reservation);
            if(id == 0){
                writer.println("实验室已被预定");
                writer.flush();
            }else if(id > 0){
                reservation.setReservationId(id);
                session.setAttribute("reservation",reservation);
            } else {
                writer.println("预约失败，请重试");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常，请重试");
            writer.flush();
        }
    }
}
