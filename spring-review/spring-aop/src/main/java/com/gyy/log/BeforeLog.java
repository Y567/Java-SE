package com.gyy.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 需求：在执行方法前，执行的日志
 */
public class BeforeLog implements MethodBeforeAdvice {

    /**
     * 前置通知
     * @param method   目标对象的执行方法
     * @param args     目标对象执行方法需要的参数
     * @param target   目标对象
     * @throws Throwable 异常
     */
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法 "+"被代理的对象是"+target.getClass().getName());
    }
}
