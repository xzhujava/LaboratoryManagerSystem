package com.laboratory.servlet;

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
 * 登录
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 11:15
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            HttpSession session = request.getSession();
            String userNo = request.getParameter("userNo");
            String userPassword = request.getParameter("userPassword");
            User loginUserInfo = new User(userNo,userPassword);
            User authentication = userService.authentication(loginUserInfo);
            if(null != authentication){
                //登陆成功保存用户信息到session
                session.setAttribute("user",authentication);
                //跳转到首页
            }else{
                writer.print("用户名或密码错误，请重试");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
