package com.gigsider.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        // TODO Auto-generated method stub
//        arg1.setHeader("Access-Control-Allow-Origin",arg0.getHeader("Origin"));
//        arg1.setHeader("Access-Control-Allow-Methods", "*");
//        arg1.setHeader("Access-Control-Allow-Credentials", "true");
//        arg1.setHeader("Access-Control-Allow-Headers", "Authorization,Origin, X-Requested-With, Content-Type, Accept,Access-Token");
//        System.out.println(arg0.getHeader("Origin"));
        System.out.println(arg0.getRequestURI());

//        //创建session
//        HttpSession session = arg0.getSession();
//
//        //无需登录，允许访问的地址
//        String[] allowUrls = new String[]{
//                "/login.do",
//                };
//
//        //获取请求地址
//        String url = arg0.getRequestURL().toString();
//
//        //获得session中的用户登陆状态
//        String userToken = null, identity = null;
//        if(session != null) {
//            userToken = (String) session.getAttribute("token");
//            identity = (String) session.getAttribute("identity");
//        }
//
//        System.out.println(session.getId());
//
//        for (String strUrl : allowUrls)
//            if(url.contains(strUrl))
//                return true;
//
//        if (session == null){
//            arg1.sendRedirect(arg0.getContextPath());
//            return false;
//        }
//
//        if(userToken != "success"){
//            arg1.sendRedirect(arg0.getContextPath());
//            return false;
//        }
//
//        if (identity == "user" && url.contains("admin")){
//            arg1.sendRedirect(arg0.getContextPath());
//            return false;
//        }
//        if (identity == "admin" && url.contains("user")){
//            arg1.sendRedirect(arg0.getContextPath());
//            return false;
//        }

        return true;
    }

}
