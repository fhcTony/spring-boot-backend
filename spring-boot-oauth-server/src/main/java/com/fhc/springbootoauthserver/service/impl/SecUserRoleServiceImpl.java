package com.fhc.springbootoauthserver.service.impl;

import com.fhc.springbootoauthserver.entity.SecUserRole;
import com.fhc.springbootoauthserver.mapper.SecRoleMapper;
import com.fhc.springbootoauthserver.mapper.SecUserRoleMapper;
import com.fhc.springbootoauthserver.service.SecUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fuhongchao
 * @create 2020/6/15 16:05
 * 用户角色服务实现类
 */
@Service
public class SecUserRoleServiceImpl implements SecUserRoleService {

    @Resource
    SecRoleMapper secRoleMapper;

    @Resource
    SecUserRoleMapper secUserRoleMapper;

    @Override
    public boolean addUserRole(String userId, String roleType) {

        String roleId = "";
        roleId = secRoleMapper.selectRoleIdByRoleType(roleType);
        SecUserRole secUserRole = new SecUserRole();
        secUserRole.setUserId(userId);
        secUserRole.setRoleId(roleId);
        try {
            int effectNum = secUserRoleMapper.insert(secUserRole);
            if(effectNum>0){
                return true;
            }else {
              throw new RuntimeException("添加用户角色信息失败！");
            }
        }catch (Exception e){
            throw new RuntimeException("添加用户角色信息失败！"+e.getMessage());
        }

    }
}
