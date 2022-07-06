package com.fhc.apicommons.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Swagger自定义配置字段
 *
 * @author fuhongchao
 * @create 2022/06/10  11:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {

    /**
     * API文档生成基础包路径
     */
    private String basePackage;
    /**
     * 是否启用登录认证
     */
    private boolean enableSecurity;
    /**
     * API文档标题
     */
    private String title;
    /**
     * API文档描述
     */
    private String description;
    /**
     * API文档版本
     */
    private String version;
    /**
     * API文档联系人姓名
     */
    private String contactName;
    /**
     * API文档联系人网址
     */
    private String contactUrl;
    /**
     * API文档联系人邮箱
     */
    private String contactEmail;
}
