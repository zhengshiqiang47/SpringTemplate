package com.dyfwz.util;

import javax.servlet.http.Cookie;

public class MyCookie {
    public static Cookie getCookie(String str)
    {
        String[] val = str.split(";")[0].split("=");
        Cookie cookie = new Cookie(val[0].toString(),val[1].toString());
        cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
        cookie.setPath("/");//设置作用域
        return cookie;
    }

    public static String getSession(String str){
        String[] val = str.split(";");
        return val[0].toString();
    }
}
