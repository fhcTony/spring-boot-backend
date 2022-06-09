package com.fhc.apigateway.common;

import lombok.Getter;

/**
 * 常用状态码和提示信息枚举
 *
 * @author fuhongchao
 */
@Getter
public enum Status implements IStatus {

    /**
     * 常用状态码和提示信息枚举
     * */
    SUCCESS(200, "操作成功！"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(403, "权限不足，请向管理员申请权限！"),
    VALIDATE_FAILED(501, "参数检验失败");

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
