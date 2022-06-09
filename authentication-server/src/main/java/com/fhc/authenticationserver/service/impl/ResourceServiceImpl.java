package com.fhc.authenticationserver.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.fhc.authenticationserver.common.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系ServiceImpl
 * @author fuhongchao
 * @create 2022/01/07  14:06
 */
@Service
public class ResourceServiceImpl {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/demo/hello", CollUtil.toList("USER"));
        resourceRolesMap.put("/api/user/curUserInfo", CollUtil.toList("ADMIN", "USER"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
