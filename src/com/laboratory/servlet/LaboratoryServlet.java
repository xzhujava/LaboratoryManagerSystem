package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.entity.Laboratory;
import com.laboratory.service.LaboratoryService;
import com.laboratory.service.impl.LaboratoryServiceImpl;

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
 * 查询实验室信息
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 11:36
 */
public class LaboratoryServlet extends HttpServlet {

    private LaboratoryService laboratoryService = new LaboratoryServiceImpl();

    private Map<String,Object> requestParam = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        requestParam = new HashMap<>(16);
        try {
            String laboratoryName = request.getParameter("laboratoryName");
            String laboratoryLocation = request.getParameter("laboratoryLocation");
            Integer status = Integer.parseInt(request.getParameter("status"));
            if(null!=laboratoryName && laboratoryName.length()>0){
                requestParam.put("laboratory_name",laboratoryName);
            }
            if(null!=laboratoryLocation && laboratoryLocation.length()>0){
                requestParam.put("laboratory_location",laboratoryLocation);
            }
            if(status != -1){
                requestParam.put("status",status);
            }
            List<Laboratory> laboratoryList = laboratoryService.getLaboratoryInfo(requestParam);
            //查询结果存入session
            //session.setAttribute("laboratoryList",laboratoryInfo);
            writer.write(JSONObject.toJSONString(R.data(laboratoryList,"查询成功")));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常，请重试");
            writer.flush();
        }
    }
}
