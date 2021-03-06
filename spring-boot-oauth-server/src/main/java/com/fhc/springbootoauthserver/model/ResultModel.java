package com.fhc.springbootoauthserver.model;

import com.fhc.springbootoauthserver.common.IStatus;
import com.fhc.springbootoauthserver.common.Status;
import com.fhc.springbootoauthserver.exception.BaseException;
import lombok.Data;

/**
 * @author fuhongchao
 * @create 2020/5/16 17:00
 */
@Data
public class ResultModel {

    //状态码
    private Integer code;

    //返回信息
    private String message;

    //返回数据
    private Object data;

    public ResultModel(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个成功且不带数据的API返回
     *
     * @return ApiResponse
     */
    public static ResultModel ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static ResultModel ofSuccess(Object data) {
        return ofStatus(Status.SUCCESS, data);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link IStatus}
     * @return ApiResponse
     */
    public static ResultModel ofStatus(IStatus status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link IStatus}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static ResultModel ofStatus(IStatus status, Object data) {
        return new ResultModel(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常的API返回
     *
     * @return ApiResponse
     */
    public static ResultModel ofException(BaseException e) {
        return new ResultModel(e.getCode(), e.getMessage(), e.getData());
    }
}
