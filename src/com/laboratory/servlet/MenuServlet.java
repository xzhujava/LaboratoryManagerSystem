package com.laboratory.servlet;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.common.api.R;
import com.laboratory.entity.Menu;
import com.laboratory.entity.User;
import com.laboratory.service.MenuService;
import com.laboratory.service.impl.MenuServiceImpl;

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
 * @date 2020/6/5 17:35
 */
public class MenuServlet extends HttpServlet {
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        try {
            User user = (User) session.getAttribute("user");
            Map<String,Object> userId = new HashMap<>();
            userId.put("user_id",user.getUserId());
            List<Menu> menuList = menuService.findMenu(userId);
            writer.write(JSONObject.toJSONString(R.data(menuList,"查询成功")));
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            writer.print("系统异常，请重试");
            writer.flush();
        }
    }
}
