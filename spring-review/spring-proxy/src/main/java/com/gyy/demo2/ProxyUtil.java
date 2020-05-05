package com.gyy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 动态代理对象的生成类，我们用这个类来生成代理对象，当然也可以使用匿名内部类的方式
 */
public class ProxyUtil implements InvocationHandler {

    //需要被代理的对象
    private Object target;

    public void setTarget(Object target){
        this.target = target;
    }

    /**
     * 这个方法用来获取代理对象
     */
    public Object getProxy(){
        /**
         * 创建代理对象的时候，我们要求代理对象与目标对象有共同的类加载器（target.getClass().getClassLoader()），以及一样的方法（target.getClass().getInterfaces()）
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     * 这个invoke方法就是用来增强方法的(来自于InvocationHandler)，我们将增强的代码写到这里
     * @param proxy  代理对象的引用，一般不用
     * @param method 被代理对象的方法
     * @param args   被代理对象的参数
     * @return       返回一个Object
     * @throws Throwable  异常
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("增强了");
        //invoke就是执行被代理对象的方法，我们就可以利用这个特性在invoke方法前后进行添油加醋
        return method.invoke(target, args);
    }


}
