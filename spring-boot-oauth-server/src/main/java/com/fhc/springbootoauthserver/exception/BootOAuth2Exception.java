package com.fhc.springbootoauthserver.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author fuhongchao
 * @create 2020/5/18 13:15
 */
@JsonSerialize(using = BootOAuthExceptionJacksonSerializer.class)
public class BootOAuth2Exception extends OAuth2Exception {

    public BootOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public BootOAuth2Exception(String msg) {
        super(msg);
    }
}
