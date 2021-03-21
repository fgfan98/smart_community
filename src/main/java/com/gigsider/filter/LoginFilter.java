package com.gigsider.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取 session
        HttpSession session = request.getSession();

        // 获得用户请求的URI
        String path = request.getRequestURI();

        // 登陆页面无需过滤
        if (
                path.indexOf("/login") > -1
                || path.indexOf(".js") > -1
                || path.indexOf(".css") > -1
                || path.indexOf(".jpg") > -1
        ) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //登录验证
        if (
                session.getAttribute("login_status") == null
                || !session.getAttribute("login_status").equals("success")
        ) {
            response.sendRedirect("/smart_community/login.html");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
