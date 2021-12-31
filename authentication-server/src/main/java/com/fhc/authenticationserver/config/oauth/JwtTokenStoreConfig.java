package com.fhc.authenticationserver.config.oauth;

import com.fhc.authenticationserver.entity.SecUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuhongchao
 * @create 2021-12-17  09:39:23
 * jwt存储token的配置
 */
@Configuration
public class JwtTokenStoreConfig {

    @Value("${jwt.signing_key}")
    private String signingKey;

    /**
     * 将jwt签名加入accessTokenConverter中
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signingKey);
        return tokenConverter;
    }

    /**
     * 对jwt生成的token定制化处理
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            SecUser user = (SecUser) authentication.getUserAuthentication().getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>(16);
            additionalInfo.put("nickname", user.getNickname());
            additionalInfo.put("phone", user.getPhone());
            additionalInfo.put("email", user.getEmail());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            //设置token的过期时间
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.HOUR, 24);
            ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
            return accessToken;
        };
    }
}
