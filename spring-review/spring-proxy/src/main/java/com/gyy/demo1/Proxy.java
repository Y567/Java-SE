package com.gyy.demo1;

import com.gyy.demo2.Landlord;
import com.gyy.demo2.Rent;

/**
 * 代理对象(中介)，实现这个租房接口就可以租房了
 */
public class Proxy implements Rent {

    //传入一个房东对象
    private Landlord landlord;

    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    public void rent() {
        extraFee();
        landlord.rent();
    }

    //扩展的业务
    private void extraFee(){
        System.out.println("收取了中介费");
    }

}
