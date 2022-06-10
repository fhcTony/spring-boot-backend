package com.fhc.apicommons.exception;

import com.fhc.apicommons.common.IStatus;

/**
 * 断言处理类，用于抛出各种API异常
 *
 * @author fuhongchao
 * @create 2022/06/10  13:21
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IStatus errorCode) {
        throw new ApiException(errorCode);
    }
}
