package com.fhc.springbootmybatis.exception.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.fhc.springbootmybatis.common.Status;
import com.fhc.springbootmybatis.exception.BaseException;
import com.fhc.springbootmybatis.model.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * @author fuhongchao
 * @create 2020/5/16 22:04
 * <p>
 * 全局统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultModel handleException(Exception e) {

        if (e instanceof NoHandlerFoundException) {
            log.error("[全局异常拦截] NoHandlerFoundException: 请求方法 {}, 请求路径 {}", ((NoHandlerFoundException) e).getHttpMethod(), ((NoHandlerFoundException) e).getRequestURL());
            return ResultModel.ofStatus(Status.REQUEST_NOT_FOUND);

        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error("[全局异常拦截] HttpRequestMethodNotSupportedException: 当前请求方式 {}, 支持的请求方式 {}", ((HttpRequestMethodNotSupportedException) e).getMethod(), JSONUtil.toJsonStr(((HttpRequestMethodNotSupportedException) e).getSupportedHttpMethods()));
            return ResultModel.ofStatus(Status.HTTP_BAD_METHOD);

        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("[全局异常拦截] MethodArgumentNotValidException: ", e);
            return new ResultModel(Status.BAD_REQUEST.getCode(), ((MethodArgumentNotValidException) e).getBindingResult()
                    .getAllErrors()
                    .get(0)
                    .getDefaultMessage(), null);

        } else if (e instanceof ConstraintViolationException) {
            log.error("[全局异常拦截] ConstraintViolationException", e);
            return new ResultModel(Status.BAD_REQUEST.getCode(), CollUtil.getFirst(((ConstraintViolationException) e).getConstraintViolations())
                    .getMessage(), null);

        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("[全局异常拦截] MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}", ((MethodArgumentTypeMismatchException) e).getName(), e.getMessage());
            return ResultModel.ofStatus(Status.PARAM_NOT_MATCH);

        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("[全局异常拦截] HttpMessageNotReadableException: 错误信息 {}", e.getMessage());
            return ResultModel.ofStatus(Status.PARAM_NOT_NULL);

        } else if (e instanceof BadCredentialsException) {
            log.error("[全局异常拦截] BadCredentialsException: 错误信息 {}", e.getMessage());
            return ResultModel.ofStatus(Status.USERNAME_PASSWORD_ERROR);

        } else if (e instanceof DisabledException) {
            log.error("[全局异常拦截] DisabledException: 错误信息 {}", e.getMessage());
            return ResultModel.ofStatus(Status.ACCOUNT_DISABLED);

        } else if (e instanceof AccessDeniedException) {
            log.error("[全局异常拦截] AccessDeniedException: 错误信息 {}", e.getMessage());
            return ResultModel.ofStatus(Status.ACCESS_DENIED);

        } else if (e instanceof BaseException) {
            log.error("[全局异常拦截] BaseException: 状态码 {}, 错误信息 {}", ((BaseException) e).getCode(), e.getMessage());
            return ResultModel.ofException((BaseException) e);

        }

        log.error("[全局异常拦截] 异常信息 {} ", e.getMessage());
        return ResultModel.ofStatus(Status.ERROR);
    }
}
