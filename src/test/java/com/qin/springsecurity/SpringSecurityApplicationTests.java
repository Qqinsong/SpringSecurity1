package com.qin.springsecurity;

import com.qin.springsecurity.mapper.MenuMapper;
import com.qin.springsecurity.mapper.UserMapper;
import com.qin.springsecurity.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired  // 属性的自动注入的注解！
    private UserMapper userMapper;
    @Test
    public void testdatabase(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void TestBCryPassword(){
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // passwordEncoder.matches()
        System.out.println(passwordEncoder.encode("1234"));
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","三更");
    }

    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void testselct(){
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

}
