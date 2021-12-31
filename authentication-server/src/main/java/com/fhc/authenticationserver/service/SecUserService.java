package com.fhc.authenticationserver.service;

import com.fhc.authenticationserver.model.dto.UserCreateDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author fuhongchao
 * @since 2020-05-16
 * 用户服务类
 */
public interface SecUserService extends UserDetailsService {

    /**
     * 创建用户
     * @param userCreateDTO 用户创建dto
     * @return true：成功，false：失败
     * */
    boolean createUser(UserCreateDTO userCreateDTO);

    /**
     * 删除用户
     * @param userId 用户id
     * @return true：成功，false：失败
     * */
    boolean deleteUser(String userId);

}
