package com.fhc.springbootoauthserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhc.springbootoauthserver.entity.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
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
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<SecUser> findByUsernameOrPhone(@Param("username") String username, @Param("phone") String phone);

    /**
     * 根据用户名获取用户id
     * */
    String getUserIdByUsername(String username);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<SecUser> findByUsernameIn(List<String> usernameList);

    int deleteByUserId(String userId);
}
