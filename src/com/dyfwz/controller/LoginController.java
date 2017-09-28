package com.dyfwz.controller;

import com.dyfwz.message.BaseMessage;
import com.dyfwz.model.User;
import com.dyfwz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CoderQiang on 2017/9/28.
 */
@Controller
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private LoginService service;

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

}
