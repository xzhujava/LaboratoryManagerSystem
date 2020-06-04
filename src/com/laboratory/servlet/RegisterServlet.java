package com.laboratory.servlet;

import com.laboratory.common.enumerate.UserTypeEnum;
import com.laboratory.entity.User;
import com.laboratory.service.UserService;
import com.laboratory.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 学生注册
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 10:15
 */
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String userName = request.getParameter("userName");
            String userNo = request.getParameter("userNo");
            String userPassword = request.getParameter("userPassword");
            String userSex = request.getParameter("userSex");
            String remark = request.getParameter("remark");
            User user = new User(userName,userNo, userPassword,userSex, UserTypeEnum.STUDENT.getValue(),remark);
            User registered = userService.registered(user);
            if(null != registered){
                //注册成功，保存用户信息到seesion
                session.setAttribute("user",registered);
                //跳转到首页
            }else{
                writer.print("注册失败，请重试");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
