package com.fhc.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fhc.springbootmybatis.entity.User;
import com.fhc.springbootmybatis.mapper.UserMapper;
import com.fhc.springbootmybatis.model.PageModel;
import com.fhc.springbootmybatis.service.UserService;
import com.fhc.springbootmybatis.model.vo.UserView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fuhongchao
 * @since 2020-05-10
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;


    @Override
    public List<UserView> getAllUsers() {

        List<User> list = userMapper.getAllUsers();

        return list.stream().map(UserView::new).collect(Collectors.toList());
    }

    @Override
    public IPage<UserView> getAllUsers(Page<?> page) {

        List<OrderItem> items=new ArrayList<>();
        items.add(OrderItem.desc("create_time"));
        page.setOrders(items);

        IPage<User> userIPage=userMapper.getAllUsers(page);

        return new PageModel<UserView>().pageModelView(userIPage.getRecords().stream().map(UserView::new).collect(Collectors.toList()),
                page.getCurrent(),page.getSize(),page.getTotal(),page.getOrders());
    }

    @Override
    public boolean addUser(User user) {

        user.setName("aabb");
        user.setPassword("12345");
        user.setSalt("12345");
        user.setEmail("xxxxx@163.com");
        user.setPhoneNumber("12345677777");
        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());
        user.setLastUpdateTime(LocalDateTime.now());

        int effectNum = userMapper.insert(user);
        if (effectNum > 0) {
            return true;
        } else {
            throw new RuntimeException("添加用户失败");
        }
    }
}
