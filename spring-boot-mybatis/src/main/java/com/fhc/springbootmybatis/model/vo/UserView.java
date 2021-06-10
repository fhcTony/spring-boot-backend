package com.fhc.springbootmybatis.model.vo;

import com.fhc.springbootmybatis.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserView {

    //用户名
    private String name;

    //邮箱
    private String email;

    //手机号码
    private String phoneNumber;

    //状态，-1：逻辑删除，0：禁用，1：启用
    private Integer status;

    //创建时间
    private LocalDateTime createTime;

    //上次登录时间
    private LocalDateTime lastLoginTime;

    //上次更新时间
    private LocalDateTime lastUpdateTime;

    public UserView(User user){
        this.name=user.getName();
        this.email=user.getEmail();
        this.phoneNumber=user.getPhoneNumber();
        this.status=user.getStatus();
        this.lastLoginTime=user.getLastLoginTime();
        this.createTime=user.getCreateTime();
        this.lastUpdateTime=user.getLastUpdateTime();
    }
}
