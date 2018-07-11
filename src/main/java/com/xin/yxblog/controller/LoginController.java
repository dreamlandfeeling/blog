package com.xin.yxblog.controller;

import com.alibaba.fastjson.JSON;
import com.xin.yxblog.dto.Constant;
import com.xin.yxblog.dto.Result;
import com.xin.yxblog.model.User;
import com.xin.yxblog.service.UserService;
import com.xin.yxblog.utils.CookieUtils;
import com.xin.yxblog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String index(){
        return "login";
    }

    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response){
        String password = (String) map.get("password");
        map.put("password",MD5Utils.md5Two(password));
        User user = userService.getByUsernameAndPassWord(map);
        if(user==null){
            return Result.error("用户名或者密码出错");
        }
        String cookieValue = UUID.randomUUID().toString();
        CookieUtils.setCookie(request,response,Constant.COOKIE_LOGIN,cookieValue,Constant.COOKIE_TIME);
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(Constant.SESSION_TIME);
        user.setPassword(null);
        session.setAttribute(Constant.SESSION_LOGIN+cookieValue,JSON.toJSON(user));
        return Result.ok();
    }

    @GetMapping("/layout")
    public String layout(HttpServletRequest request,HttpServletResponse response){
        String cookieValue = CookieUtils.getCookieValue(request, Constant.COOKIE_LOGIN);
        HttpSession session = request.getSession(true);
        session.removeAttribute(Constant.SESSION_LOGIN+cookieValue);
        CookieUtils.deleteCookie(request,response,Constant.COOKIE_LOGIN);
        return "/";
    }
}
