package com.fhc.authenticationserver.config.oauth;

import com.fhc.authenticationserver.config.oauth.component.RestfulAccessDeniedHandler;
import com.fhc.authenticationserver.config.oauth.component.RestfulAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 * @author fuhongchao
 * @create 2020/5/18 15:03
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId).stateless(true)
                //自定义Token异常信息,用于token校验失败返回信息
                .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                //授权异常处理
                .accessDeniedHandler(restfulAccessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/api/rsa/publicKey").permitAll()
                .antMatchers("/actuator/**").permitAll()
                //静态资源不拦截
                .antMatchers("/",
                        "/webjars/**",
                        "/favicon.ico",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .anyRequest().authenticated();
    }
}
