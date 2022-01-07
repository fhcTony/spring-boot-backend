package com.fhc.authenticationserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhc.authenticationserver.entity.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 用户Mapper
 * @author fuhongchao
 * @since 2020-05-16
 */
public interface SecUserMapper extends BaseMapper<SecUser> {

    /**
     * 查询全部用户信息
     * @return 用户列表
     * */
    List<SecUser> getAllSecUsers();

    /**
     * 根据用户名、手机号查询用户
     *
     * @param username 用户名
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<SecUser> findByUsernameOrPhone(@Param("username") String username, @Param("phone") String phone);

    /**
     * 根据用户名获取用户id
     * @param username 用户名
     * @return 用户id
     * */
    String getUserIdByUsername(String username);

    /**
     * 根据用户名列表查询用户列表
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<SecUser> findByUsernameIn(List<String> usernameList);

    /**
     * 根据用户id删除用户
     * @param userId 用户id
     * @return true：成功，false：失败
     * */
    int deleteByUserId(String userId);
}
