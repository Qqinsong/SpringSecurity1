package com.qin.springsecurity.controller;

import com.qin.springsecurity.pojo.ResponseResult;
import com.qin.springsecurity.pojo.User;
import com.qin.springsecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        // 登录
        return loginService.login(user);
    }
    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
