package com.fhc.apicommons.common.constant;

/**
 * 权限相关常量声明
 *
 * @author fuhongchao
 * @create 2022/06/05  08:32
 */
public class AuthConstant {

    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    public static final String ADMIN_CLIENT_ID = "client-admin";

    /**
     * 后台管理接口路径匹配
     */
    public static final String ADMIN_URL_PATTERN = "/admin/**";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息请求头
     */
    public static final String USER_TOKEN_HEADER = "user";

}
