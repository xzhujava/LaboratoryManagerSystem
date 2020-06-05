package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.common.enumerate.UserTypeEnum;
import com.laboratory.entity.Menu;
import com.laboratory.entity.User;
import com.laboratory.service.MenuService;
import com.laboratory.service.UserService;
import com.laboratory.service.impl.MenuServiceImpl;
import com.laboratory.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
 * 学生注册
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 10:15
 */
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

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
            User user = new User(userName,userNo, userPassword,userSex,remark);
            User registered = userService.registered(user);
            if(null != registered){
                //注册成功，保存用户信息到seesion
                session.setAttribute("user",registered);
                //注册成功，查询菜单
                Map<String,Object> userId = new HashMap<>();
                userId.put("user_id",registered.getUserId());
                List<Menu> menu = menuService.findMenu(userId);
                session.setAttribute("menu",menu);
                writer.write(JSONObject.toJSONString(R.data(registered,"注册成功")));
                writer.flush();
                //因为前端是发送的ajax请求，所以这里直接把登录成功的消息返回给前端，由前端去跳转
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
