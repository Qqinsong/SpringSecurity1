package com.qin.springsecurity.service;

import com.qin.springsecurity.pojo.LoginUser;
import com.qin.springsecurity.pojo.ResponseResult;
import com.qin.springsecurity.pojo.User;
import com.qin.springsecurity.utils.JwtUtil;
import com.qin.springsecurity.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        // 进行认证管理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 调用数据库，自动的调用 UserDetails 进行数据的认证

        // 如果认证没有通过则给出相应的提示信息
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或者密码错误");
        }
        // 如果认证通过了，使用userid生成一个jwt返回给前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        redisCache.setCacheObject("login:"+userId,loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"登录成功",map);
        // 把用户的完整信息存入到redis中

    }

    // 注销功能就是删除Redis中对应的数据！
    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }
}
