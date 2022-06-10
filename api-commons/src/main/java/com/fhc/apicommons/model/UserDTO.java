package com.fhc.apicommons.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户信息
 *
 * @author fuhongchao
 * @create 2022/06/10  11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String username;
    private String password;
    private Boolean status;
    private String clientId;
    private List<String> roles;
}
