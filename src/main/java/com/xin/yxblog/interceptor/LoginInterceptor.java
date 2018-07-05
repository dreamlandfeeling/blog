package com.xin.yxblog.interceptor;

import com.alibaba.fastjson.JSON;
import com.xin.yxblog.dto.Constant;
import com.xin.yxblog.model.User;
import com.xin.yxblog.utils.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            response.sendRedirect("/login");
            return false;
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(Constant.COOKIE_LOGIN)){
                String token = cookie.getValue();
                HttpSession session = request.getSession();
                User user = JSON.toJavaObject((JSON) session.getAttribute(Constant.SESSION_LOGIN + token),User.class) ;
                if(!StringUtils.isNotBlank(token)){
                    response.sendRedirect("/login");
                    return false;
                }
                if(user==null){
                    response.sendRedirect("/login");
                    return false;
                }
            }
        }
        return true;
    }
}
