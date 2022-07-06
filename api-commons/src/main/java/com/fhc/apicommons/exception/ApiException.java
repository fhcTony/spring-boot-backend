package com.fhc.apicommons.exception;

import com.fhc.apicommons.common.IStatus;

/**
 * 自定义API异常
 *
 * @author fuhongchao
 * @create 2022/06/10  13:16
 */
public class ApiException extends RuntimeException{

    private IStatus errorCode;

    public ApiException(IStatus errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IStatus getErrorCode() {
        return errorCode;
    }
}
