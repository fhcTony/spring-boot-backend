package com.fhc.springbootmybatis.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Api(value = "", description = "")
@Slf4j
public class TestController {

    Map<String,Integer> map=new HashMap<>();

    @GetMapping("/register")
    public void register(String username,String password){
        log.info("查询数据库该用户名是否已经存在");
        if(!map.containsKey(username)){
            map.put(username,1);
        }else {
            log.info("map中已经存在该值！");
        }

        log.info(map.toString());
    }
}
