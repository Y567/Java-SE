package com.gyy.config;

import com.gyy.pojo.User;
import com.gyy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userServiceImpl;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("走授权模块");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        User principal = (User) subject.getPrincipal();

        //取出当前用户对应的权限,并设置
        info.addStringPermission(principal.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("走认证模块");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(token==null){
            return null;
        }
        //假设这里有一个从数据库中获取的用户名
        User user = userServiceImpl.findUserByUsername(token.getUsername());
        System.out.println(user);
        if(user == null){
            //用户名不对,他会帮我门自动抛异常，我们返回null后会判断
            return null;
        }
        //密码认证，shiro做,这里将数据库中查出来的用户传递进去，授权的时候会用到
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
