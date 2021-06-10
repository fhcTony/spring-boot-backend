package com.fhc.springbootmybatis.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;

/**
 * @author fuhongchao
 * @create 2020/5/16 21:27
 */
public class IdConfig {

    //雪花主键生成器
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}
