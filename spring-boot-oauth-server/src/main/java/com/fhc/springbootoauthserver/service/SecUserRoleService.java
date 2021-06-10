package com.fhc.springbootoauthserver.service;

/**
 * @author fuhongchao
 * @create 2020/6/15 16:03
 */
public interface SecUserRoleService {

    /**
     * 给用户赋予角色权限
     */
    boolean addUserRole(String userId, String roleType);
}
