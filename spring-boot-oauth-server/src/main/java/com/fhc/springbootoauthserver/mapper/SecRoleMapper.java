package com.fhc.springbootoauthserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fhc.springbootoauthserver.entity.SecRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-16
 */
public interface SecRoleMapper extends BaseMapper<SecRole> {

    List<SecRole> selectRolesByUserId(String id);

    String selectRoleIdByRoleType(String roleType);
}
