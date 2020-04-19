package com.gyy.SpringBootStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootStudyApplication {
    /**
     * springboot通过main方法启动
     * 这个类所在的包进行全包扫描，相当于spring的context-scan指定
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyApplication.class, args);
    }

}
