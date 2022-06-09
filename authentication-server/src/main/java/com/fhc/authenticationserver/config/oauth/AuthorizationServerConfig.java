package com.fhc.authenticationserver.config.oauth;

import com.fhc.authenticationserver.common.exception.oauth.BootOAuth2WebResponseExceptionTranslator;
import com.fhc.authenticationserver.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * 授权服务器配置
 *
 * @author fuhongchao
 * @create 2020/5/18 14:59
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecUserService userDetailsService;
    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.scope}")
    private String scope;
    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetails) throws Exception {

        // 配置客户端
        clientDetails.inMemory()
                .withClient(clientId)
                .secret(new BCryptPasswordEncoder().encode(clientSecret))
                .scopes(scope)
                .resourceIds(resourceId)
                // 授权模式
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("oauth2")
                // 设置token过期时间
                .accessTokenValiditySeconds(60*60*24)
                .refreshTokenValiditySeconds(60*60*24*7);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                // token存储策略
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                // 允许POST请求获取token，即访问端点：/oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
        endpoints.reuseRefreshTokens(true);
        // oauth2登录异常处理
        endpoints.exceptionTranslator(new BootOAuth2WebResponseExceptionTranslator());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("permitAll()");
    }


}
