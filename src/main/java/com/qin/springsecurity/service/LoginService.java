package com.qin.springsecurity.service;

import com.qin.springsecurity.pojo.ResponseResult;
import com.qin.springsecurity.pojo.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
