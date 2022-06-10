package com.fhc.authenticationserver.service.impl;

import com.fhc.authenticationserver.entity.SecRole;
import com.fhc.authenticationserver.entity.SecUser;
import com.fhc.authenticationserver.mapper.SecRoleMapper;
import com.fhc.authenticationserver.mapper.SecUserMapper;
import com.fhc.authenticationserver.model.dto.user.UserAddOrModifyDTO;
import com.fhc.authenticationserver.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户ServiceImpl
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

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SecUser secUser = secUserMapper.findByUsernameOrPhone(s, s)
                .orElseThrow(() -> new UsernameNotFoundException("未找到名为 " + s + " 的用户"));

        List<SecRole> roles = secRoleMapper.selectRolesByUserId(secUser.getId());

        secUser.setRoles(roles);

        return secUser;
    }

    @Override
    public boolean addUser(UserAddOrModifyDTO userAddOrModifyDTO) {

        SecUser user=new SecUser();
        user.setUsername(userAddOrModifyDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userAddOrModifyDTO.getPassword()));
        user.setNickname(userAddOrModifyDTO.getNickname());
        user.setPhone(userAddOrModifyDTO.getPhone());
        user.setEmail(userAddOrModifyDTO.getEmail());
        user.setBirthday(userAddOrModifyDTO.getBirthday().toString());
        user.setSex(userAddOrModifyDTO.getSex());

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
