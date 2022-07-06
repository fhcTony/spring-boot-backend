package com.fhc.testapiserver.model.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author fuhongchao
 * @create 2022/06/05  11:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private List<String> roles;
}
