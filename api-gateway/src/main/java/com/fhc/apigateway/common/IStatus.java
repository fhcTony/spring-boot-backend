package com.fhc.apigateway.common;

/**
 * 状态码接口
 *
 * @author fuhongchao
 */
public interface IStatus {

    /**
     * 状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 返回信息
     *
     * @return 返回信息
     */
    String getMessage();

}