package com.fhc.authenticationserver.model.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * 用户创建DTO
 * @author fuhongchao
 * @create 2020/6/14 22:39
 */
@Data
public class UserCreateDTO {


    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String email;

    private LocalDate birthday;

    private Integer sex;

}
