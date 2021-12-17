package com.fhc.springbootoauthserver.controller;

import com.fhc.springbootoauthserver.common.Status;
import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.mapper.SecUserMapper;
import com.fhc.springbootoauthserver.model.ResultModel;
import com.fhc.springbootoauthserver.model.vo.SecUserVO;
import com.fhc.springbootoauthserver.model.dto.UserCreateDTO;
import com.fhc.springbootoauthserver.service.SecUserRoleService;
import com.fhc.springbootoauthserver.service.SecUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fuhongchao
 * @create 2020/6/15 9:44
 * 用户操作controller
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户接口", description = "用户增删改查相关接口")
public class SecUserController {

    @Autowired
    private SecUserService secUserService;

    @Resource
    SecUserMapper secUserMapper;

    @Autowired
    private SecUserRoleService secUserRoleService;

    @ApiOperation(value = "创建用户", notes = "新增用户信息")
    @PostMapping("/create")
    @ApiImplicitParam(name = "userCreateDTO", value = "用户注册信息", required = true, dataType = "UserCreateDTO")
    public ResultModel createUser(@RequestBody UserCreateDTO userCreateDTO) {

        boolean createUserSuccess = secUserService.createUser(userCreateDTO);
        if (createUserSuccess) {
            String userId = secUserMapper.getUserIdByUsername(userCreateDTO.getUsername());
            boolean addUserRole = secUserRoleService.addUserRole(userId, "普通用户");
            if (addUserRole) {
                return ResultModel.ofStatus(Status.USER_CREATE_SUCCESS);
            } else {
                return ResultModel.ofStatus(Status.USER_ROLE_ADD_FAILED);
            }

        } else {
            return ResultModel.ofStatus(Status.USER_CREATE_FAILED);
        }

    }

    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String")
    public ResultModel deleteUser(String username){

        SecUser user=(SecUser) secUserService.loadUserByUsername(username);
        if(!StringUtils.isEmpty(user.getUsername())&&user.getUsername().equals(username)){
            boolean isDeleteSuccess=secUserService.deleteUser(user.getId());
            if(isDeleteSuccess){
                return ResultModel.ofSuccess();
            }else {
                return ResultModel.ofStatus(Status.USER_DELETE_FAILED);
            }
        }else {
            return ResultModel.ofStatus(Status.USER_NOT_FOUND);
        }
    }

    @ApiOperation(value = "获取当前登录用户的用户信息", notes = "获取当前登录用户的用户信息")
    @GetMapping("/info")
    public ResultModel getCurrentUserInfo(){

        SecUser user= (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResultModel.ofSuccess(new SecUserVO(user));
    }
}
