package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.entity.Reservation;
import com.laboratory.service.ReservationService;
import com.laboratory.service.impl.ReservationServiceImpl;
import com.laboratory.vo.ReservationVO;

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
        PrintWriter writer = response.getWriter();
        map = new HashMap<>(16);
        try {
            String userNo = request.getParameter("userNo");
            if(null != userNo && userNo.length()>0){
                map.put("user_no",userNo);
            }
            String status = request.getParameter("status");
            if(null != status && status.length()>0){
                int st = Integer.parseInt(status);
                if(st>-1){
                    map.put("status",status);
                }
            }
            String laboratoryId = request.getParameter("laboratoryId");
            if(null != laboratoryId && laboratoryId.length()>0){
                if(!laboratoryId.equals("-1")){
                    map.put("laboratory_id",laboratoryId);
                }
            }
            List<ReservationVO> appointList = reservationService.getAppointInfos(map);
            writer.write(JSONObject.toJSONString(R.data(appointList,"查询成功")));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
