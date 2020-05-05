package com.gyy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig1 {
    @Bean
    public String getString(){
        return "阿狗已经";
    }
}
