package com.qin.springsecurity.controller;

import com.qin.springsecurity.pojo.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller 控制层的注解，restController返回统一的JSON字符串，不会再走这个视图解析器！
@RestController
public class Hello {

    // 这个注解的意思代表的是浏览器的请求路径的地址
    // 如果放在类上的话，代表的是父路径
    @RequestMapping("/h1")
    @PreAuthorize("hasAnyAuthority('system:dept:list')")
    public String hello(){
        return "hello,springboot222";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors(){
        return new ResponseResult(200,"cros");
    }
}
