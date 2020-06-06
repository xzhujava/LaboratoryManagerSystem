package com.laboratory.filter;

import com.laboratory.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 系统全局过滤器
 * <ol>
 * <li>Authentication 判断用户是否登录</li>
 * <li>EncdingFilter 字符集过滤UTF8</li>
 * </ol>
 * @author 小柱IT
 * @version 1.0
 * @date 2020/6/3 20:38
 */
public class SysFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("login") || requestURI.contains("register")){
            chain.doFilter(req, resp);
        }else{
            HttpSession session = request.getSession();
            PrintWriter writer = response.getWriter();
            User user = (User) session.getAttribute("user");
            if(null == user){
                writer.write("<script>alert('请求未授权，请重新登录')</script>");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }else{
                chain.doFilter(req, resp);
            }
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
