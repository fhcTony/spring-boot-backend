package com.fhc.authenticationserver.common;

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
     * 提示信息
     *
     * @return 提示信息
     */
    String getMessage();

}