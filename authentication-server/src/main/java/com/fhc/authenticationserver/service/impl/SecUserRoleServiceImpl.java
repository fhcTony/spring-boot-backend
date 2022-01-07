package com.fhc.authenticationserver.service.impl;

import com.fhc.authenticationserver.entity.SecUserRole;
import com.fhc.authenticationserver.mapper.SecRoleMapper;
import com.fhc.authenticationserver.mapper.SecUserRoleMapper;
import com.fhc.authenticationserver.service.SecUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色ServiceImpl
 * @author fuhongchao
 * @create 2020/6/15 16:05
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
