package com.gyy.springbootdatasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

//    后台监控程序
    @Bean
    public ServletRegistrationBean s(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        HashMap<String, String> map = new HashMap<>();
        //允许所有人访问,value为空表示默认所有人都可以
        map.put("allow","127.0.0.1");

        //设置登录密码和账户
        map.put("loginUsername","admin");  //loginUsername和loginPassword事固定的
        map.put("loginPassword","123456");

        bean.setInitParameters(map);

        return bean;
    }

}
