package com.fhc.authenticationserver.service.impl;

import cn.hutool.core.collection.CollUtil;
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

    /**
     * Redis缓存权限规则key
     */
    private static final String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    @PostConstruct
    public void initData() {
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/api/hello", CollUtil.toList("USER"));
        resourceRolesMap.put("/api/user/info", CollUtil.toList("ADMIN", "USER"));
        redisTemplate.opsForHash().putAll(RESOURCE_ROLES_MAP_KEY, resourceRolesMap);
    }
}
