package com.fhc.authenticationserver.config;

import com.fhc.apicommons.config.BaseSwaggerConfig;
import com.fhc.apicommons.model.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Swagger文档配置
 *
 * @author fuhongchao
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .basePackage("com.fhc.authenticationserver.controller")
                .title("认证服务 APIs")
                .description("认证服务API详情")
                .contactName("fuhongchao")
                .contactEmail("fhcTony@outlook.com")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
