package com.fhc.testapiserver.controller;

import com.fhc.testapiserver.common.LoginUserHolder;
import com.fhc.testapiserver.model.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuhongchao
 * @create 2022/06/05  11:04
 */
@RestController
public class DemoController {

    @Autowired
    private LoginUserHolder loginUserHolder;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World.";
    }

    @GetMapping("/api/user/curUserInfo")
    public UserDTO currentUser() {
        return loginUserHolder.getCurrentUser();
    }
}
