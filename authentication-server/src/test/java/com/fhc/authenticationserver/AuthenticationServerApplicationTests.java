package com.fhc.authenticationserver;

import com.fhc.authenticationserver.entity.SecUser;
import com.fhc.authenticationserver.mapper.SecUserMapper;
import com.fhc.authenticationserver.model.dto.user.UserAddOrModifyDTO;
import com.fhc.authenticationserver.service.SecUserService;
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
public class AuthenticationServerApplicationTests {

    public AuthenticationServerApplicationTests(){}

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
        UserAddOrModifyDTO userAddOrModifyDTO =new UserAddOrModifyDTO();
        userAddOrModifyDTO.setUsername("abc");
        userAddOrModifyDTO.setPassword("abc");
        userAddOrModifyDTO.setNickname("hahaha");
        userAddOrModifyDTO.setPhone("13456796530");
        userAddOrModifyDTO.setEmail("abc@126.com");
        userAddOrModifyDTO.setBirthday(LocalDate.of(1996,6,24));
        userAddOrModifyDTO.setSex(1);
        boolean isSuccess=secUserService.createUser(userAddOrModifyDTO);
        if(isSuccess){
            log.info("用户创建成功： {}", userAddOrModifyDTO.toString());
        }else {
            log.info("用户创建失败!");
        }
    }
}
