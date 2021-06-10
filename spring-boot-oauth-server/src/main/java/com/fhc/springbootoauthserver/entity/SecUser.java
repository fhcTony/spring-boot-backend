package com.fhc.springbootoauthserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //手机
    private String phone;
    //邮箱
    private String email;
    //生日
    private LocalDateTime birthday;
    //性别，男-1，女-2
    private Integer sex;
    //状态，启用-1，禁用-0
    private boolean enabled=true;
    //逻辑删除，删除-1，未删除-0
    private boolean isDelete=false;
    //创建时间
    private LocalDateTime createTime;
    //更新时间
    private LocalDateTime updateTime;

    //用户角色列表
    private List<SecRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(SecRole role : roles){
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
        return enabled;
    }
}
