package com.fhc.authenticationserver.controller;

import com.fhc.apicommons.common.constant.AuthConstant;
import com.fhc.apicommons.model.ResultModel;
import com.fhc.authenticationserver.model.vo.user.Oauth2TokenVO;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author fuhongchao
 * @create 2022/01/07  11:26
 */
@Api(tags = "AuthController", description = "token登录认证")
@RestController
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "获取token", notes = "获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "客户端id", required = true),
            @ApiImplicitParam(name = "client_secret", value = "客户端秘钥", required = true),
            @ApiImplicitParam(name = "scope", value = "scope", required = true)
    })
    @PostMapping("/oauth/token")
    public ResultModel<Oauth2TokenVO> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        assert oAuth2AccessToken != null;
        Oauth2TokenVO oauth2TokenDto = Oauth2TokenVO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        return ResultModel.success(oauth2TokenDto);
    }

    @ApiOperation(value = "获取RSA公钥", notes = "获取RSA公钥")
    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
