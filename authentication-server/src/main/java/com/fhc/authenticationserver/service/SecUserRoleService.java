package com.fhc.authenticationserver.service;

/**
 * 用户角色Service
 * @author fuhongchao
 * @create 2020/6/15 16:03
 */
public interface SecUserRoleService {

    /**
     * 给用户赋予角色权限
     * @param userId 用户id
     * @param roleType 角色类型
     * @return true：成功，false：失败
     */
    boolean addUserRole(String userId, String roleType);
}
