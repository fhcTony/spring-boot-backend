package com.fhc.authenticationserver.exception.oauth;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author fuhongchao
 * @create 2020/5/18 13:15
 */
@JsonSerialize(using = BootOAuthExceptionJacksonSerializer.class)
public class BootOAuth2Exception extends OAuth2Exception {

    public BootOAuth2Exception(String message, Throwable t) {
        super(message, t);
    }

    public BootOAuth2Exception(String message) {
        super(message);
    }
}
