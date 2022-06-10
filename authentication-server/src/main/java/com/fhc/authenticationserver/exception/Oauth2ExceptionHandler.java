package com.fhc.authenticationserver.exception;

import com.fhc.apicommons.model.ResultModel;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局处理Oauth2抛出的异常
 *
 * @author fuhongchao
 * @create 2022/06/10  14:07
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultModel handleOauth2(OAuth2Exception e) {
        return ResultModel.failed(e.getMessage());
    }
}
