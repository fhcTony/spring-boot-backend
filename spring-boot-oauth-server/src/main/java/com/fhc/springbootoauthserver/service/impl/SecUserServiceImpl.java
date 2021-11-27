package com.fhc.springbootoauthserver.service.impl;

import com.fhc.springbootoauthserver.entity.SecRole;
import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.mapper.SecRoleMapper;
import com.fhc.springbootoauthserver.mapper.SecUserMapper;
import com.fhc.springbootoauthserver.model.dto.UserCreateDTO;
import com.fhc.springbootoauthserver.service.SecUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-16
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
                .orElseThrow(() -> new UsernameNotFoundException("未找到用户信息：" + s));

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
