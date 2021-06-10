package com.fhc.springbootoauthserver.service;

import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.model.SecUserView;
import com.fhc.springbootoauthserver.model.dto.UserCreateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-16
 */
public interface SecUserService extends UserDetailsService {

    /**
     * 创建用户
     * */
    boolean createUser(UserCreateDTO userCreateDTO);

    /**
     * 删除用户
     * */
    boolean deleteUser(String userId);

}
