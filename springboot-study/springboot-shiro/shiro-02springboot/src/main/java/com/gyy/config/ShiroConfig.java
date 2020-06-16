package com.gyy.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


@Configuration
public class ShiroConfig {
    @Bean
    //1.自定义Realm
    public MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    //2.defaultWebSecurityManager
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联myRealm
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    //3.ShiroFilterFactoryBean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);

        /**
         * 可以设置过滤器
         * anon:无需认证就可以访问
         * authc:必须认证才可以访问
         * user:必须拥有 记住我 才可以使用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         *
         */
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //设置这两个路径的访问权限
//        map.put("/user/add","authc");
//        System.out.println("dqqofqm");
//        map.put("/user/update","authc");

        //授权
        map.put("/user/add","perms[user:add]");
        map.put("/user/update","perms[user:update]");

        bean.setFilterChainDefinitionMap(map);

        //设置没权限跳转到登录页面
        bean.setLoginUrl("/user/toLogin");
        return bean;
    }
}
