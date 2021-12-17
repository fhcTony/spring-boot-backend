package com.fhc.springbootoauthserver.config.oauth;

import com.fhc.springbootoauthserver.exception.AuthExceptionEntryPoint;
import com.fhc.springbootoauthserver.exception.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author fuhongchao
 * @create 2020/5/18 15:03
 * 资源服务器配置
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId).stateless(true)
                //自定义Token异常信息,用于token校验失败返回信息
                .authenticationEntryPoint(new AuthExceptionEntryPoint())
                //授权异常处理
                .accessDeniedHandler(new MyAccessDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/api/user/createUser").permitAll()
                .antMatchers("/actuator/**").permitAll()
                //静态资源不拦截
                .antMatchers("/webjars/**",
                        "/favicon.ico",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs",
                        "/resources/**")
                .permitAll()
                .anyRequest().authenticated();
        // @formatter:on
    }
}
