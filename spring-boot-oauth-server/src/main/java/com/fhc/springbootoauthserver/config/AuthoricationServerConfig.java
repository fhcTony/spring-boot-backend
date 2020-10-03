package com.fhc.springbootoauthserver.config;

import com.fhc.springbootoauthserver.entity.SecUser;
import com.fhc.springbootoauthserver.exception.BootOAuth2WebResponseExceptionTranslator;
import com.fhc.springbootoauthserver.service.SecUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuhongchao
 * @create 2020/5/18 14:59
 */
@Configuration
@EnableAuthorizationServer
public class AuthoricationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private SecUserService userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Value("${jwt.signing_key}")
    private String signing_key;

    @Value("${oauth2.resource_id}")
    private String resource_id;

    @Value("${oauth2.client_id}")
    private String client_id;

    @Value("${oauth2.client_secret}")
    private String client_secret;

    @Value("${oauth2.scope}")
    private String scope;

    //设置的jwt签名加入accessTokenConverter中
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(signing_key);
        return tokenConverter;
    }

    //Redis保存生成的token
    @Bean
    public TokenStore tokenStore() {


        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        //key前缀
        tokenStore.setPrefix("fhc_");
        //new JwtTokenStore(accessTokenConverter())
        return tokenStore;
    }

    //jwt生成token定制化处理
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            SecUser user = (SecUser) authentication.getUserAuthentication().getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("userId", user.getId());
            additionalInfo.put("phone", user.getPhone());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            //设置token的过期时间60分钟
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.MINUTE, 60);
            ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
            return accessToken;
        };
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //配置客户端
        clients.inMemory().withClient(client_id)
                .resourceIds(resource_id)
                //授权模式
                .authorizedGrantTypes("password", "refresh_token")
                .scopes(scope)
                .authorities("oauth2")
                .secret(new BCryptPasswordEncoder().encode(client_secret));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                //允许GET、POST方法请求获取token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        endpoints.reuseRefreshTokens(true);

        //oauth2登录异常处理
        endpoints.exceptionTranslator(new BootOAuth2WebResponseExceptionTranslator());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        //允许表单认证
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("permitAll()");
    }


}
