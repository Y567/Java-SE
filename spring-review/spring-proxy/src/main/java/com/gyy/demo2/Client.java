package com.gyy.demo2;

public class Client {

    public static void main(String[] args) {
        //房东
        Landlord landlord = new Landlord();
        ProxyUtil proxyUtil = new ProxyUtil();
        //设置需要被代理的对象（即我们的房东）
        proxyUtil.setTarget(landlord);
        //我们其实可以强转为被代理对象的类型
        Rent proxy = (Rent) proxyUtil.getProxy();
        proxy.rent();
    }
}
