package com.fhc.authenticationserver.controller;

import com.fhc.authenticationserver.model.ResultModel;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * 获取RSA公钥Controller
 * @author fuhongchao
 * @create 2022/01/07  11:26
 */
@RestController
@RequestMapping("/api/rsa")
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public ResultModel getPublicKey(){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return ResultModel.ofSuccess(new JWKSet(key).toJSONObject());
    }
}
