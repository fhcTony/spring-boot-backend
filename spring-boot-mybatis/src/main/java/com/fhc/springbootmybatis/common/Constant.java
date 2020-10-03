package com.fhc.springbootmybatis.common;

/**
 * @author fuhongchao
 * @create 2020/5/16 16:27
 */

/**
 * 常量池
 */
public interface Constant {

    //启用
    Integer ENABLE = 1;
    //禁用
    Integer DISABLE = 0;
    //页面
    Integer PAGE = 1;
    //按钮
    Integer BUTTON = 2;
    //JWT在Redis中保存的key前缀
    String REDIS_JWT_KEY_PREFIX = "security:jwt:";
    //星号
    String SYMBOL_STAR = "*";
    //邮箱符号
    String SYMBOL_EMAIL = "@";
    //默认当前页码
    Integer DEFAULT_CURRENT_PAGE = 1;
    //默认每页条数
    Integer DEFAULT_PAGE_SIZE = 10;
    //匿名用户的用户名
    String ANONYMOUS_NAME = "匿名用户";
}
