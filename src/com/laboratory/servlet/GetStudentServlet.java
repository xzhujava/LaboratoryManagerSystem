package com.laboratory.servlet;

import com.laboratory.entity.User;
import com.laboratory.service.UserService;
import com.laboratory.service.impl.UserServiceImpl;
import com.sun.org.apache.xpath.internal.objects.XNull;

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
 * 查询学生信息
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 15:11
 */
public class GetStudentServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    private Map<String,Object> map = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        map = new HashMap<>();
        try {
            String userName = request.getParameter("userName");
            if(null != userName && userName.length()>0){
                map.put("user_name",userName);
            }
            String userNo = request.getParameter("userNo");
            if(null != userNo && userNo.length()>0){
                map.put("user_no",userNo);
            }
            List<User> studentList = userService.findStudent(map);
            session.setAttribute("studentList",studentList);
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
