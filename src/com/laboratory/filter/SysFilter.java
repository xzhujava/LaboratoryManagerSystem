package com.laboratory.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 系统全局过滤器
 * <ol>
 * <li>Authentication 判断用户是否登录</li>
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
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
