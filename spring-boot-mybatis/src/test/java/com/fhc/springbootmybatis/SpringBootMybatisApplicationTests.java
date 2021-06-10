package com.fhc.springbootmybatis;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhc.springbootmybatis.entity.User;
import com.fhc.springbootmybatis.mapper.UserMapper;
import com.fhc.springbootmybatis.model.vo.UserView;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootMybatisApplicationTests {

    public SpringBootMybatisApplicationTests() {

    }

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void test() throws Exception {
        log.info("查询数据...");
        Page<User> page = new Page<>(1, 3);//参数一是当前页，参数二是每页个数
        IPage<User> users = userMapper.getAllUsers(page);
        List<User> list = users.getRecords();

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("users", list);

        log.info("查询结果为： {}", valueOperations.get("users"));

    }

    @Test
    public void queryUserForPage() {
        Page<User> page = new Page<>(1, 3);//参数一是当前页，参数二是每页个数
        //IPage<User> users = userMapper.selectPage(page, null);
        IPage<User> users = userMapper.getAllUsers(page);
        List<User> list = users.getRecords();
        for (User user : list) {
            log.info(user.toString());
        }
    }

}
