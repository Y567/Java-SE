package com.gyy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//安全框架主要有两个功能,授权和认证
@EnableWebSecurity   //这个注解的作用是开启安全功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //认证，给用户赋予权限不同的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //可以选择不同的地方去处理，还可以从数据库中取出角色，我们暂时用内存中的,springboot后面需要给密码加密
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("gyy").password(new BCryptPasswordEncoder().encode("yy5201314")).roles("vip2","vip3")
                //可以利用and去连接，添加更多的用户
        .and().withUser("ag").password(new BCryptPasswordEncoder().encode("ag")).roles("vip1","vip2","vip3");
    }

    //这是授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //访问首页所有人都可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                //只有VIP1的人可以访问
                .antMatchers("/level1/*").hasRole("vip1")
                .antMatchers("/level2/*").hasRole("vip2")
                .antMatchers("/level3/*").hasRole("vip3");

        //没有权限的跳转到登录页面，让他登录(自定义登录界面)
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        //开启记住我的功能
        http.rememberMe().rememberMeParameter("rememberMe");

        //注销功能，如果成功则跳转到首页
        http.logout().logoutSuccessUrl("/");
    }
}
