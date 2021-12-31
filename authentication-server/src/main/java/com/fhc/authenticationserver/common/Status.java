package com.fhc.authenticationserver.common;

import lombok.Getter;

/**
 * @author fuhongchao
 * 状态码和返回信息枚举
 */
@Getter
public enum Status implements IStatus {

    /**
     * 基本
     */
    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器内部错误！"),
    LOGOUT(200, "退出成功！"),

    /**
     * 用户操作
     */
    USER_CREATE_SUCCESS(201,"创建用户成功！"),
    USER_CREATE_FAILED(400,"创建用户失败！"),
    USER_DELETE_SUCCESS(200,"删除用户成功！"),
    USER_DELETE_FAILED(400,"删除用户失败！"),
    USER_ROLE_ADD_FAILED(400,"添加用户角色失败！"),
    USER_NOT_FOUND(400,"当前用户不存在！"),

    /**
     * 用户鉴权
     * */
    UNAUTHORIZED(401, "未鉴权，请先登录！"),
    BAD_CREDENTIALS(401, "用户名或密码错误，登录失败！"),
    ACCOUNT_LOCKED(401, "帐户已被锁定，请联系管理员解锁！"),
    ACCOUNT_DISABLED(401, "帐户已被禁用，请联系管理员恢复！"),
    ACCOUNT_EXPIRED(401, "账户已过期，无法登录！"),
    CREDENTIALS_EXPIRED(401, "密码已过期，无法登录！"),
    ACCESS_DENIED(403, "权限不足，请先向管理员申请权限！"),

    /**
     * 请求
     * */
    REQUEST_NOT_FOUND(404, "当前请求不存在！"),
    HTTP_BAD_METHOD(405, "请求方式不支持！"),
    BAD_REQUEST(400, "当前请求异常！"),
    PARAM_NOT_MATCH(400, "参数不匹配！"),
    PARAM_NOT_NULL(400, "参数不能为空！"),

    /**
     * token相关
     * */
    TOKEN_EXPIRED(5001, "token已过期，请重新登录！"),
    TOKEN_OUT_OF_CTRL(5002, "你已在别处登录，如非本人登陆，请检查账户安全性！");

    private final Integer code;

    private final String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(" Status:{code=%s, message=%s} ", getCode(), getMessage());
    }

}
