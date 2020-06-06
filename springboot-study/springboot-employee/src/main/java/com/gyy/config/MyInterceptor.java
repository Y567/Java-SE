package com.gyy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义一个拦截器
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 返回true放行，false拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        //放行
        return true;
    }
}
