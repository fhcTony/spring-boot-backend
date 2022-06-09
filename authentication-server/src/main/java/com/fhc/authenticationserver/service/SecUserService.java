package com.fhc.authenticationserver.service;

import com.fhc.authenticationserver.model.dto.user.UserAddOrModifyDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 用户Service
 * @author fuhongchao
 * @since 2020-05-16
 */
public interface SecUserService extends UserDetailsService {

    /**
     * 添加用户
     * @param userAddOrModifyDTO 用户创建dto
     * @return true：成功，false：失败
     * */
    boolean addUser(UserAddOrModifyDTO userAddOrModifyDTO);

    /**
     * 删除用户
     * @param userId 用户id
     * @return true：成功，false：失败
     * */
    boolean deleteUser(String userId);

}
