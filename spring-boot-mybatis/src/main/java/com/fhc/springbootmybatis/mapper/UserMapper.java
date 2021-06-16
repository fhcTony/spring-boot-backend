package com.fhc.springbootmybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhc.springbootmybatis.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-10
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getAllUsers();

    IPage<User> getAllUsers(Page<?> page);

}
