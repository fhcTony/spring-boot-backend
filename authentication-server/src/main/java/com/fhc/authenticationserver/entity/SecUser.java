package com.fhc.authenticationserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户类
 * @author fuhongchao
 * @since 2020-05-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     * */
    @TableId
    private String id;
    /**
     * 用户名
     * */
    private String username;
    /**
     * 密码
     * */
    private String password;
    /**
     * 昵称
     * */
    private String nickname;
    /**
     * 手机号
     * */
    private String phone;
    /**
     * 邮箱
     * */
    private String email;
    /**
     * 生日
     * */
    private String birthday;
    /**
     * 性别：男-1，女-2
     * */
    private Integer sex;
    /**
     * 账户状态：启用-1，禁用-0
     * */
    private boolean status = true;
    /**
     * 逻辑删除：删除-1，未删除-0
     * */
    private boolean logicDelete = false;
    /**
     * 创建时间
     * */
    private LocalDateTime createTime;
    /**
     * 更新时间
     * */
    private LocalDateTime updateTime;
    /**
     * 最后登录时间
     * */
    private LocalDateTime lastLoginTime;

    /**
     * 用户角色列表
     * */
    private List<SecRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (SecRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
