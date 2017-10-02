package com.dyfwz.controller;

import com.dyfwz.message.BaseMessage;
import com.dyfwz.model.User;
import com.dyfwz.service.LoginService;
import com.dyfwz.util.Encrypt;
import com.dyfwz.util.MyCookie;
import com.google.gson.JsonObject;
import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CoderQiang on 2017/9/28.
 */
@Controller
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private LoginService service;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final HashMap<String, List<String>> cookieStore = new HashMap<String, List<String>>();
    OkHttpClient client = new OkHttpClient();
    private User user;

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseMessage loginUser(
            @RequestParam(value = "userName")String userName,
            @RequestParam(value = "userPass")String userPass,
            HttpServletRequest request
    ){
        BaseMessage message = new BaseMessage();
        User user=new User();
        user.setUserPass(userPass);
        user.setUserName(userName);
        service.addUser(user);
        message.status=1;
        message.result=1;
        return message;

    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public String loginTest(
            @RequestParam(value = "userName")String userName,
            @RequestParam(value = "userPass")String userPass,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws JSONException, IOException {
        Encrypt encrypt = new Encrypt();
        String pass = encrypt.SHA512(userPass);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("IsAutoLogin","0");
        jsonObject.put("UniversityCode","10386");
        jsonObject.put("LoginName",userName);
        jsonObject.put("Password",pass);
        Response ret = null;
        System.out.println(jsonObject.toString());
        request.getSession().setAttribute("FzuCookie",userName);
        System.out.println("cookieId:"+request.getSession().getId()+" FzuCookie");
//        try
//        {
//            ret = post("http://swms.fzu.edu.cn/MobileLogin/StudentLogin",jsonObject.toString());
//            if (ret.isSuccessful())
//            {
//                String session = MyCookie.getSession(ret.header("Set-Cookie"));
//                System.out.println("Set-Cookie:"+ret.header("Set-Cookie"));
//                request.getSession().setAttribute("FzuCookie",userName);
//                System.out.println(request.getSession().getAttribute("cookieId:"+request.getSession().getId()+"FzuCookie"));
//            }
//        }
//        catch (IOException e )
//        {
//            System.out.println(e);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
        return "ok";
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    public String loginTest2(@RequestParam(value = "userName")String userName,
            HttpServletRequest request){
        String str = "aaa";
        System.out.println(request.getSession().getAttribute("FzuCookie")+" CookieId:"+request.getSession().getId());
//        try
//        {
//            str = post2("http://swms.fzu.edu.cn/MobileInfoAdmin/GetStudentInfoById","ASP.NET_SessionId=sotoqe453zpshq31brjdd245");
//            System.out.println(str);
//        }
//        catch (IOException e )
//        {
//            System.out.println(e);
//        }
        return str;
    }

    Response post(String url, String json) throws IOException, URISyntaxException {
        RequestBody formBody = new FormEncodingBuilder()
                .add("data", json)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .header("content-type" ,"application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept","*/*")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    String post2(String url, String cookie) throws IOException {
        RequestBody formBody = new FormEncodingBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .header("content-type" ,"application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept","*/*")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }
    @Controller
    public class loginAction {

        @RequestMapping("login.do")
        public String login(String username,String password){
            if ("admol".equals(username)) {
                System.out.println(username +" 登录成功");
                return "loginSuccess";//逻辑视图名       跳转页面默认为转发
            }
            return "loginError";
        }
    }
}
