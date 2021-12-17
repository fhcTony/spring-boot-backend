package com.fhc.springbootoauthserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhc.springbootoauthserver.entity.SecRole;

import java.util.List;

/**
 * @author fuhongchao
 * @since 2020-05-16
 * 用户角色Mapper类
 */
public interface SecRoleMapper extends BaseMapper<SecRole> {

    /**
     * 根据用户id查找角色信息
     * @param id 用户id
     * @return roles
     * */
    List<SecRole> selectRolesByUserId(String id);

    /**
     * 根据角色类型查找角色id
     * @param roleType 角色类型
     * @return roleId
     * */
    String selectRoleIdByRoleType(String roleType);
}
