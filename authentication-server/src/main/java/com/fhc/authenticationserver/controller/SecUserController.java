package com.fhc.authenticationserver.controller;

import com.fhc.authenticationserver.common.Status;
import com.fhc.authenticationserver.entity.SecUser;
import com.fhc.authenticationserver.mapper.SecUserMapper;
import com.fhc.authenticationserver.model.ResultModel;
import com.fhc.authenticationserver.model.vo.SecUserVO;
import com.fhc.authenticationserver.model.dto.UserCreateDTO;
import com.fhc.authenticationserver.service.SecUserRoleService;
import com.fhc.authenticationserver.service.SecUserService;
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

    @ApiOperation(value = "删除用户", notes = "根据用户名删除指定用户")
    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String")
    public ResultModel deleteUser(@PathVariable String username){

        SecUser user=(SecUser) secUserService.loadUserByUsername(username);
        if(!StringUtils.isEmpty(user.getUsername())&&user.getUsername().equals(username)){
            boolean isDeleteSuccess=secUserService.deleteUser(user.getId());
            if(isDeleteSuccess){
                return ResultModel.ofStatus(Status.USER_DELETE_SUCCESS);
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
