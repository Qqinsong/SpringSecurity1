package com.qin.springsecurity.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qin.springsecurity.mapper.MenuMapper;
import com.qin.springsecurity.mapper.UserMapper;
import com.qin.springsecurity.pojo.LoginUser;
import com.qin.springsecurity.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
// 定义一个类重写UserDetillsService中的方法，使用数据库中查询的信息
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户的信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        // 如果查询不到数据就通过异常进行抛出进行提示
        if(Objects.isNull(user)){
            throw new RuntimeException("账户名或者密码错误");
        }
        // TODO 根据用户名查询权限信息 天骄到LoginUser中
        //List<String> list = new ArrayList<>(Arrays.asList("test"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        // 把查询到的对象封装成UserDetails对象放回,里面还带有这个权限的信息！
        return new LoginUser(user,list);
    }
}
