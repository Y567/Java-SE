package com.gyy.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //记得开启swagger
public class SwaggerConfig {

//    我们可以根据环境选择开启和关闭swagger


    @Bean
//    自定义swagger的配置信息
    public Docket docket(Environment e){

        //设置环境
        Profiles profile = Profiles.of("dev");
        //如果当前环境是指定的dev那么就返回true
        boolean flag = e.acceptsProfiles(profile);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //我们想指定扫描指定的包,指定扫描方式RequestHandlerSelectors
                .apis(RequestHandlerSelectors.basePackage("com.gyy.swagger.controller"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "阿狗的传奇一生",
                "Api Documentation",
                "1.0",
                "urn:tos",
                new Contact("阿狗通讯录","https://agchuangqi.com","274378@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }



}
