package com.gyy.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 利用aop来进行获取用户的ip,以及访问路径，后台返回的数据
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //封装了日志信息
    private class LogContent{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        LogContent(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "LogContent{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

    //定义一个切面
    @Pointcut("execution(* com.gyy.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //1.0获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        //2.0获取url和访客ip地址
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        //3.0获取参数和请求的方法
        String classMethod = joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        LogContent logContent = new LogContent(url, ip, classMethod, args);

        logger.info("用户的请求的内容:{}",logContent);
    }

    @After("log()")
    public void doAfter(){
//        logger.info("服务器处理完毕");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("服务器端返回的内容:{}",result);
    }

}
