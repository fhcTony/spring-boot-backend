package com.fhc.authenticationserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户-角色类
 * @author fuhongchao
 * @create 2020/6/15 15:52
 */
@Data
public class SecUserRole {

    @TableId(type = IdType.AUTO)
    private long id;

    private String userId;

    private String roleId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
