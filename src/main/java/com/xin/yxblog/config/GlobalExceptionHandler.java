package com.xin.yxblog.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LogManager.getLogger(this.getClass());

    private static final String DEFAULT_ERROR_VIEW = "/error/exception";

    @ExceptionHandler(value = Exception.class)
    public Object defaultExceptionHandler(HttpServletRequest request,Exception e){
        logger.error("出现错误QAQ："+e.getMessage(),e);
        return DEFAULT_ERROR_VIEW;
    }

}
