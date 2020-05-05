package com.gyy.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 自定义切面,什么是切面？我的理解就是一个类，类里面的方法可以作为切入方法，切入到其他类的方法中
 */
@Aspect  //定义为切面类
public class UserAspect {

    //前置通知
    @Before("execution(* com.gyy.service.impl.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("执行之前===============");
    }

    //后置通知
    @After("execution(* com.gyy.service.impl.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("执行之后===============");
    }

    @Around("execution(* com.gyy.service.impl.UserServiceImpl.*(..))")
    public void advist(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");
        joinPoint.proceed();  //相当于执行方法
        System.out.println("环绕后");
    }

}
