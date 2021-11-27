package com.fhc.springbootoauthserver;

import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.mapper.SecUserMapper;
import com.fhc.springbootoauthserver.model.dto.UserCreateDTO;
import com.fhc.springbootoauthserver.service.SecUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SpringBootOauthServerApplicationTests {

    public SpringBootOauthServerApplicationTests(){}

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    SecUserMapper secUserMapper;

    @Autowired
    private SecUserService secUserService;

    @Test
    public void test() {

        String password="technology";

        String encoded_password=bCryptPasswordEncoder.encode(password);

        log.info(encoded_password);
    }

    @Test
    public void testUser(){

        List<SecUser> list=secUserMapper.getAllSecUsers();

        for(SecUser user:list){
            log.info(user.toString());
        }
    }

    @Test
    public void createUser(){
        UserCreateDTO userCreateDTO=new UserCreateDTO();
        userCreateDTO.setUsername("abc");
        userCreateDTO.setPassword("abc");
        userCreateDTO.setNickname("hahaha");
        userCreateDTO.setPhone("13456796530");
        userCreateDTO.setEmail("abc@126.com");
        userCreateDTO.setBirthday(LocalDate.of(1996,6,24));
        userCreateDTO.setSex(1);
        boolean isSuccess=secUserService.createUser(userCreateDTO);
        if(isSuccess){
            log.info("用户创建成功： {}",userCreateDTO.toString());
        }else {
            log.info("用户创建失败!");
        }
    }
}
