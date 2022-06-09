package com.fhc.authenticationserver.common;

import lombok.Getter;

/**
 * 常用状态码和提示信息枚举
 * @author fuhongchao
 */
@Getter
public enum Status implements IStatus {

    /**
     * 基本
     */
    SUCCESS(200, "操作成功！"),
    FAILED(500, "操作失败！"),
    VALIDATE_FAILED(500, "参数校验失败"),

    /**
     * 用户操作
     */
    USER_ADD_SUCCESS(201,"创建用户成功！"),
    USER_ADD_FAILED(500,"创建用户失败！"),
    USER_DELETE_SUCCESS(200,"删除用户成功！"),
    USER_DELETE_FAILED(500,"删除用户失败！"),
    USER_ROLE_ADD_FAILED(500,"添加用户角色失败！"),
    USER_NOT_FOUND(500,"当前用户不存在！"),

    /**
     * 用户鉴权
     * */
    UNAUTHORIZED(401, "暂未登录或token已过期！"),
    BAD_CREDENTIALS(401, "用户名或密码错误，登录失败！"),
    ACCOUNT_LOCKED(401, "帐户已被锁定，请联系管理员解锁！"),
    ACCOUNT_DISABLED(401, "帐户已被禁用，请联系管理员恢复！"),
    ACCOUNT_EXPIRED(401, "账户已过期，无法登录！"),
    CREDENTIALS_EXPIRED(401, "密码已过期，无法登录！"),
    FORBIDDEN(403, "权限不足，请向管理员申请权限！"),

    /**
     * 请求
     * */
    REQUEST_NOT_FOUND(404, "当前请求不存在！"),
    HTTP_BAD_METHOD(405, "请求方式不支持！"),
    BAD_REQUEST(400, "当前请求异常！"),

    /**
     * token相关
     * */
    TOKEN_EXPIRED(5001, "token已过期，请重新登录！"),
    TOKEN_OUT_OF_CTRL(5002, "你已在别处登录，如非本人登陆，请检查账户安全性！");

    /**
     * 状态码
     * */
    private final Integer code;

    /**
     * 提示信息
     * */
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
