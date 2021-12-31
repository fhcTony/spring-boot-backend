package com.fhc.authenticationserver.model.vo;

import com.fhc.authenticationserver.entity.SecUser;
import lombok.Data;

/**
 * @author fuhongchao
 * 用户信息VO
 */
@Data
public class SecUserVO {

    private String username;

    private String nickname;

    private String phone;

    private String email;

    private String birthday;

    private Integer sex;

    private Boolean enabled;

    public SecUserVO(SecUser user){
        this.username=user.getUsername();
        this.nickname=user.getNickname();
        this.phone=user.getPhone();
        this.email=user.getEmail();
        this.birthday=user.getBirthday();
        this.sex=user.getSex();
        this.enabled=user.isEnabled();
    }
}
