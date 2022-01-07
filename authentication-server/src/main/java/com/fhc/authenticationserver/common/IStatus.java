package com.fhc.authenticationserver.common;

/**
 * REST API 状态码接口
 * @author fuhongchao
 */
public interface IStatus {

    /**
     * 获得状态码
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获得返回信息
     * @return 返回信息
     */
    String getMessage();

}