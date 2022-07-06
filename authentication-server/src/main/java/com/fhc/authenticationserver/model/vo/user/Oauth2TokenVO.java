package com.fhc.authenticationserver.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Token返回信息封装
 *
 * @author fuhongchao
 * @create 2022/06/10  15:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenVO {

    @ApiModelProperty("token令牌")
    private String token;
    @ApiModelProperty("刷新token令牌")
    private String refreshToken;
    @ApiModelProperty("token令牌前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private Integer expiresIn;
}
