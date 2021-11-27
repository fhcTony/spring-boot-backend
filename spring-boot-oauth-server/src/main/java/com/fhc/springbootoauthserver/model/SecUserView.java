package com.fhc.springbootoauthserver.model;

import com.fhc.springbootoauthserver.entity.SecUser;
import lombok.Data;

@Data
public class SecUserView {

    //用户名
    private String username;
    //昵称
    private String nickname;
    //手机
    private String phone;
    //邮箱
    private String email;
    //生日
    private String birthday;
    //性别，男-1，女-2
    private Integer sex;
    //状态，启用-1，禁用-0
    private boolean enabled=true;

    public SecUserView(SecUser user){
        this.username=user.getUsername();
        this.nickname=user.getNickname();
        this.phone=user.getPhone();
        this.email=user.getEmail();
        this.birthday=user.getBirthday();
        this.sex=user.getSex();
        this.enabled=user.isEnabled();
    }
}
