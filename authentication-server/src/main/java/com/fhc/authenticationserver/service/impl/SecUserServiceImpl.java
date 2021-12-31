package com.fhc.authenticationserver.service.impl;

import com.fhc.authenticationserver.entity.SecRole;
import com.fhc.authenticationserver.entity.SecUser;
import com.fhc.authenticationserver.mapper.SecRoleMapper;
import com.fhc.authenticationserver.mapper.SecUserMapper;
import com.fhc.authenticationserver.model.dto.UserCreateDTO;
import com.fhc.authenticationserver.service.SecUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fuhongchao
 * @since 2020-05-16
 * 用户服务实现类
 */
@Service
public class SecUserServiceImpl implements SecUserService {

    @Resource
    private SecUserMapper secUserMapper;

    @Resource
    private SecRoleMapper secRoleMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SecUser secUser = secUserMapper.findByUsernameOrPhone(s, s)
                .orElseThrow(() -> new UsernameNotFoundException("未找到名为 " + s + " 的用户"));

        List<SecRole> roles = secRoleMapper.selectRolesByUserId(secUser.getId());

        secUser.setRoles(roles);

        return secUser;
    }

    @Override
    public boolean createUser(UserCreateDTO userCreateDTO) {

        SecUser user=new SecUser();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userCreateDTO.getPassword()));
        user.setNickname(userCreateDTO.getNickname());
        user.setPhone(userCreateDTO.getPhone());
        user.setEmail(userCreateDTO.getEmail());
        user.setBirthday(userCreateDTO.getBirthday().toString());
        user.setSex(userCreateDTO.getSex());

        try {
            int effectNum=secUserMapper.insert(user);
            if(effectNum>0){
                return true;
            }else {

                throw new RuntimeException("创建用户失败！");
            }
        }catch (Exception e){

            throw new RuntimeException("创建用户失败！"+e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(String userId) {

        try {
            int effectNum=secUserMapper.deleteByUserId(userId);
            if(effectNum>0){
                return true;
            }else {
                throw new RuntimeException("删除用户失败");
            }
        }catch (Exception e){
            throw new RuntimeException("删除用户失败！"+e.getMessage());
        }
    }

}
