package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.common.api.ResultCode;
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
 * 登录
 * @author 张栓
 * @version 1.0
 * @date 2020/6/4 11:15
 */
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

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
                //注册成功，查询菜单
                Map<String,Object> userId = new HashMap<>();
                userId.put("user_id",authentication.getUserId());
                List<Menu> menuList = menuService.findMenu(userId);
                session.setAttribute("menuList",menuList);
                writer.write(JSONObject.toJSONString(R.data(authentication,"登陆成功")));
                writer.flush();
                //因为前端是发送的ajax请求，所以这里直接把登录成功的消息返回给前端，由前端去跳转
            }else{
                writer.print("学号或密码错误，请重试");
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
