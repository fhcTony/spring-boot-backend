package com.fhc.authenticationserver.controller;

import com.fhc.authenticationserver.common.Status;
import com.fhc.authenticationserver.entity.SecUser;
import com.fhc.authenticationserver.model.ResultModel;
import com.fhc.authenticationserver.model.dto.user.UserAddOrModifyDTO;
import com.fhc.authenticationserver.model.vo.user.SecUserVO;
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

/**
 * @author fuhongchao
 * @create 2020/6/15 9:44
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/api")
public class SecUserController {

    @Autowired
    private SecUserService userService;
    @Autowired
    private SecUserRoleService userRoleService;

    @ApiOperation(value = "新增或修改用户信息", notes = "新增或修改用户信息")
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/admin/user/addOrModify")
    @ApiImplicitParam(name = "userAddOrModifyDTO", value = "用户信息", required = true, dataType = "UserAddOrModifyDTO")
    public ResultModel addOrModifyUser(@RequestBody UserAddOrModifyDTO userAddOrModifyDTO) {

        boolean addUserSuccess = userService.addUser(userAddOrModifyDTO);
        if (addUserSuccess) {
            SecUser user = (SecUser) userService.loadUserByUsername(userAddOrModifyDTO.getUsername());
            String userId = user.getId();
            boolean addUserRole = userRoleService.addUserRole(userId, "普通用户");
            if (addUserRole) {
                return ResultModel.status(Status.USER_ADD_SUCCESS);
            } else {
                return ResultModel.status(Status.USER_ROLE_ADD_FAILED);
            }

        } else {
            return ResultModel.status(Status.USER_ADD_FAILED);
        }

    }

    @ApiOperation(value = "删除用户", notes = "根据用户名删除指定用户")
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/admin/user/delete/{username}")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    public ResultModel deleteUser(@PathVariable String username) {

        SecUser user = (SecUser) userService.loadUserByUsername(username);
        if (StringUtils.isNotBlank(user.getUsername()) && user.getUsername().equals(username)) {
            boolean isDeleteSuccess = userService.deleteUser(user.getId());
            if (isDeleteSuccess) {
                return ResultModel.status(Status.USER_DELETE_SUCCESS);
            } else {
                return ResultModel.status(Status.USER_DELETE_FAILED);
            }
        } else {
            return ResultModel.status(Status.USER_NOT_FOUND);
        }
    }

    @ApiOperation(value = "获取当前登录用户的用户信息", notes = "获取当前登录用户的用户信息")
    @GetMapping("/user/curUserInfo")
    public ResultModel<SecUserVO> getCurUserInfo() {
        SecUser user = (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResultModel.success(new SecUserVO(user));
    }
}
