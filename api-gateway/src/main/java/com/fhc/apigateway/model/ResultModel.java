package com.fhc.apigateway.model;

import com.fhc.apigateway.common.IStatus;
import com.fhc.apigateway.common.Status;
import lombok.Data;

/**
 * API统一返回对象
 *
 * @author fuhongchao
 * @create 2022/06/05  09:00
 */
@Data
public class ResultModel<T> {

    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 提示信息
     * */
    private String message;
    /**
     * 返回的数据
     * */
    private T data;

    protected ResultModel() {
    }

    protected ResultModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     */
    public static <T> ResultModel<T> success(T data) {
        return new ResultModel<T>(Status.SUCCESS.getCode(), Status.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param  message 提示信息
     * @param data 返回的数据
     */
    public static <T> ResultModel<T> success(String message, T data) {
        return new ResultModel<T>(Status.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultModel<T> failed() {
        return failed(Status.FAILED);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResultModel<T> failed(String message) {
        return new ResultModel<T>(Status.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ResultModel<T> failed(IStatus errorCode) {
        return new ResultModel<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message 提示信息
     */
    public static <T> ResultModel<T> failed(IStatus errorCode,String message) {
        return new ResultModel<T>(errorCode.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResultModel<T> validateFailed() {
        return failed(Status.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResultModel<T> validateFailed(String message) {
        return new ResultModel<T>(Status.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultModel<T> unauthorized(T data) {
        return new ResultModel<T>(Status.UNAUTHORIZED.getCode(), Status.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultModel<T> forbidden(T data) {
        return new ResultModel<T>(Status.FORBIDDEN.getCode(), Status.FORBIDDEN.getMessage(), data);
    }

}
