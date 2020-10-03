package com.fhc.springbootmybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhc.springbootmybatis.entity.User;
import com.fhc.springbootmybatis.model.vo.UserView;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author fuhongchao
 * @create  2020-05-10
 */
public interface UserService {

    /**
     * 查询所有用户
     * */
    List<UserView> getAllUsers();

    /**
     * 查询所有用户
     * */
    IPage<UserView> getAllUsers(Page<?> page);

    /**
     * 添加用户
     * */
    boolean addUser(User user);

}
