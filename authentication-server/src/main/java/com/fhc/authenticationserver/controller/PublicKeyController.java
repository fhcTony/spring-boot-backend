package com.fhc.authenticationserver.controller;

import com.fhc.authenticationserver.model.ResultModel;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * @author fuhongchao
 * @create 2022/01/07  11:26
 */
@Api(tags = "RSA公钥")
@RestController
@RequestMapping("/api")
public class PublicKeyController {

    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "获取RSA公钥", notes = "获取RSA公钥")
    @GetMapping("/rsa/publicKey")
    public ResultModel getPublicKey(){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return ResultModel.success(new JWKSet(key).toJSONObject());
    }
}
