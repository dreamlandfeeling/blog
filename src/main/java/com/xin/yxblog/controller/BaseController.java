package com.xin.yxblog.controller;

import com.alibaba.fastjson.JSON;
import com.xin.yxblog.dto.Constant;
import com.xin.yxblog.model.User;
import com.xin.yxblog.utils.CookieUtils;
import com.xin.yxblog.utils.HttpContextUtils;
import com.xin.yxblog.utils.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    public User getUser(){
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String userToken = CookieUtils.getCookieValue(request, Constant.COOKIE_LOGIN);
        if(!StringUtils.isNotBlank(userToken)){
            return null;
        }
        Object json = request.getSession(true).getAttribute(Constant.SESSION_LOGIN + userToken);
        if(json==null){
            return null;
        }
        User user = JSON.toJavaObject((JSON) json ,User.class) ;
        if(user==null){
            return null;
        }
        return user;
    }

    public Integer getUserId(){
        User user = getUser();
        if(user==null){
            return null;
        }
        return user.getId();
    }

}
