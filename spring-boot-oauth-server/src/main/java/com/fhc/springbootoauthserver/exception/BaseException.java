package com.fhc.springbootoauthserver.exception;

import com.fhc.springbootoauthserver.common.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fuhongchao
 * 异常基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

	private Integer code;
	private String message;
	private Object data;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
}
