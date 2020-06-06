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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/6 14:40
 */
public class GetAllLaboratoryNameServlet extends HttpServlet {
    private LaboratoryService laboratoryService= new LaboratoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            List<Laboratory> laboratoryList = laboratoryService.getAllName();
            writer.write(JSONObject.toJSONString(R.data(laboratoryList,"查询成功")));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统异常，请重试");
            writer.flush();
        }
    }
}
