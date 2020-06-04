package com.laboratory.servlet;

import com.laboratory.entity.Reservation;
import com.laboratory.service.ReservationService;
import com.laboratory.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 15:45
 */
public class GetAppointServlet extends HttpServlet {

    private ReservationService reservationService = new ReservationServiceImpl();

    private Map<String,Object> map = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        map = new HashMap<>(16);
        try {
            String userNo = request.getParameter("userNo");
            if(null != userNo && userNo.length()>0){
                map.put("user_no",userNo);
            }
            String userName = request.getParameter("userName");
            if(null != userName && userName.length()>0){
                map.put("user_name",userName);
            }
            String laboratoryName = request.getParameter("laboratoryName");
            if(null != laboratoryName && laboratoryName.length()>0){
                map.put("laboratory_name",laboratoryName);
            }
            List<Reservation> appointList = reservationService.getAppointInfos(map);
            session.setAttribute("appointList",appointList);
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
