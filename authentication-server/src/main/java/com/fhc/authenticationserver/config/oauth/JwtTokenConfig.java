package com.fhc.authenticationserver.config.oauth;

import com.fhc.authenticationserver.entity.SecUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt存储token的配置
 *
 * @author fuhongchao
 * @create 2021-12-17  09:39:23
 */
@Configuration
public class JwtTokenConfig {

    @Value("${jwt.signing_key}")
    private String signingKey;
    @Value("${jwt.keypair.file}")
    private String file;
    @Value("${jwt.keypair.key}")
    private String key;
    @Value("${jwt.keypair.pwd}")
    private String pwd;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // 将jwt签名加入jwtAccessTokenConverter中
        // tokenConverter.setSigningKey(signingKey);
        // 将jwt密钥对加入jwtAccessTokenConverter中
        tokenConverter.setKeyPair(keyPair());
        return tokenConverter;
    }

    /**
     * 从classpath下的证书中获取秘钥对
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(file), pwd.toCharArray());
        return keyStoreKeyFactory.getKeyPair(key, pwd.toCharArray());
    }

    /**
     * 对jwt生成的token定制化处理
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            SecUser user = (SecUser) authentication.getUserAuthentication().getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>(16);
            additionalInfo.put("username", user.getUsername());
            additionalInfo.put("nickname", user.getNickname());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
