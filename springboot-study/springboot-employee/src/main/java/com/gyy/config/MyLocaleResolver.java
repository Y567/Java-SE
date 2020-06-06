package com.gyy.config;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 这是为实现国际化重写的地域解析器，获取请求参数中的参数，创建不同的localeResolver
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        if(lang == null || lang.length() == 0){
            //返回一个默认的解析就好
            return Locale.getDefault();
        }
        //有值，我们就需要获取字符串中的值，创建不同的解析
        String[] s = lang.split("_");
        return new Locale(s[0],s[1]);
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
