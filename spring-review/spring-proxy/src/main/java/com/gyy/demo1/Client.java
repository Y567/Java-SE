package com.gyy.demo1;

import com.gyy.demo2.Landlord;

/**
 * 我们,客户需要租房子
 */
public class Client {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        Proxy proxy = new Proxy(landlord);
        //我们去找代理对象租
        proxy.rent(); //此时已经有了额外的业务
    }
}
