package com.gyy.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice  //可以拦截所有被Controller注释的请求
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //所有的异常都能处理
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL:{},Exception:{}",request.getRequestURL(),e);

        //这里我们处理一下特殊的找不到资源的异常，找不到资源不是服务器的错，所有要抛出NotFound
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            //说明由ResponseStatus注释下存在e这个异常，所以需要跑出去
            throw e;
        }else{
            //返回一个错误页面
            ModelAndView mv = new ModelAndView();
            mv.addObject("url",request.getRequestURL());
            mv.addObject("exception",e);
            mv.setViewName("error/error");

            return mv;
        }
    }
}
