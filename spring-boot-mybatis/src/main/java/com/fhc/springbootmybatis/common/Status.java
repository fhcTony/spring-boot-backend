package com.fhc.springbootmybatis.common;

import lombok.Getter;

/**
 * <p>
 * 通用状态码
 * </p>
 */
@Getter
public enum Status implements IStatus {

    SUCCESS(200, "操作成功！"),

    FAILED(500, "操作失败！"),

    ERROR(500, "服务器内部错误！"),

    LOGOUT(200, "退出成功！"),

    UNAUTHORIZED(401, "未鉴权，请先登录！"),

    ACCOUNT_LOCKED(401, "当前用户已被锁定，请联系管理员解锁！"),

    ACCOUNT_DISABLED(401, "当前用户已被禁用，请联系管理员恢复！"),

    BAD_CREDENTIALS(401, "用户名或密码输入错误，登录失败！"),

    ACCOUNT_EXPIRED(401, "账户已过期，登录失败！"),

    CREDENTIALS_EXPIRED(401, "密码已过期，登录失败！"),

    ACCESS_DENIED(403, "权限不足，请先申请权限！"),

    REQUEST_NOT_FOUND(404, "当前请求不存在！"),

    HTTP_BAD_METHOD(405, "请求方式不支持！"),

    BAD_REQUEST(400, "当前请求异常！"),

    PARAM_NOT_MATCH(400, "参数不匹配！"),

    PARAM_NOT_NULL(400, "参数不能为空！"),

    USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误！"),

    TOKEN_EXPIRED(5002, "token已过期，请重新登录！"),

    TOKEN_PARSE_ERROR(5002, "token解析失败，请尝试重新登录！"),

    TOKEN_OUT_OF_CTRL(5003, "当前用户已在别处登录，请尝试更改密码或重新登录！"),

    KICK_OUT_SELF(5004, "无法手动踢出自己，请尝试退出登录操作！");

    //状态码
    private final Integer code;

    //返回信息
    private final String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Status fromCode(Integer code) {
        Status[] statuses = Status.values();
        for (Status status : statuses) {
            if (status.getCode()
                    .equals(code)) {
                return status;
            }
        }
        return SUCCESS;
    }

    @Override
    public String toString() {
        return String.format(" Status:{code=%s, message=%s} ", getCode(), getMessage());
    }

}
