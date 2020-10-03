package com.fhc.springbootoauthserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
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
