package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.service.LaboratoryService;
import com.laboratory.service.ReservationService;
import com.laboratory.service.impl.LaboratoryServiceImpl;
import com.laboratory.service.impl.ReservationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 15:25
 */
public class ChangeStatusServlet extends HttpServlet {

    private LaboratoryService laboratoryService = new LaboratoryServiceImpl();
    private ReservationService reservationService = new ReservationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            String laboratoryId = req.getParameter("laboratoryId");
            String reservationId = req.getParameter("reservationId");
            Boolean laboratoryStatus = laboratoryService.changeStatus(laboratoryId);
            Boolean reservationStatus = reservationService.changeStatus(reservationId);
            if(laboratoryStatus && reservationStatus){
                writer.write(JSONObject.toJSONString(R.data("操作成功")));
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常，请重试");
            writer.flush();
        }
    }
}
