package com.fhc.springbootoauthserver;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.mapper.SecUserMapper;
import com.fhc.springbootoauthserver.model.dto.UserCreateDTO;
import com.fhc.springbootoauthserver.service.SecUserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        userCreateDTO.setBirthday(LocalDateTime.of(1996,6,24,15,36,23,1000000));
        userCreateDTO.setSex(1);
        boolean isSuccess=secUserService.createUser(userCreateDTO);
        if(isSuccess){
            log.info("用户创建成功： {}",userCreateDTO.toString());
        }else {
            log.info("用户创建失败!");
        }
    }

    @Test
    public void getUUID(){
        String uuid= UUID.randomUUID().toString();
        log.info(uuid);
        String uuid_1= UuidUtils.generateUuid();
        log.info(uuid_1);
    }

}
