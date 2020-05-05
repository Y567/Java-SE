package com.gyy.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 需求：将这个日志的输出信息，增加到用户业务类的方法执行完毕后
 */
public class AfterLog implements AfterReturningAdvice {

    /**
     * 最终通知，在方法执行并返回后，执行
     * @param returnValue  返回值
     * @param method       目标执行的方法
     * @param args         目标执行方法的参数
     * @param target       目标对象
     * @throws Throwable   异常
     */
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("返回值"+returnValue+" "+"执行了"+method.getName()+"方法 "+"被代理的对象是"+target.getClass().getName());
    }
}
