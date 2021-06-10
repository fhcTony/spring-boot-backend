package com.fhc.springbootmybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhc.springbootmybatis.common.Status;
import com.fhc.springbootmybatis.entity.User;
import com.fhc.springbootmybatis.model.ResultModel;
import com.fhc.springbootmybatis.service.UserService;
import com.fhc.springbootmybatis.model.vo.UserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "", description = "")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/test")
    public String test() {
        return restTemplate.getForObject("http://nacos-provider/hello", String.class);
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/getAllUsers")
    public ResultModel getAllUsers() {

        List<UserView> list = userService.getAllUsers();
        return ResultModel.ofSuccess(list);

    }

    @ApiOperation(value = "分页获取用户信息", notes = "")
    @GetMapping("/getAllUsersByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", example = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", example = "3", dataType = "Integer")
    })
    public ResultModel getAllUsersByPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize) {

        Page<?> page = new Page<>(pageNum, pageSize);
        IPage<UserView> list = userService.getAllUsers(page);
        return ResultModel.ofSuccess(list);

    }

    @ApiOperation(value = "", notes = "")
    @PostMapping("/addUser")
    public ResultModel addUser(@RequestBody User user) {

        boolean addSuccess = userService.addUser(user);
        if (addSuccess) {
            return ResultModel.ofSuccess();
        } else {
            return ResultModel.ofStatus(Status.FAILED);
        }

    }

    @GetMapping("/test1")
    public String test1() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info(authentication.getName());



        return "dskldsk";
    }
}

