package com.xin.yxblog.filter;

import com.xin.yxblog.config.XssAndSqlHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssAndSqlFilter  implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         javax.servlet.FilterChain chain) throws IOException,
            ServletException {
        //此类继承了HttpServletRequestWrapper，可以对请求参数进行过滤
        XssAndSqlHttpServletRequestWrapper xssRequest = new XssAndSqlHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssRequest, response);

    }
}