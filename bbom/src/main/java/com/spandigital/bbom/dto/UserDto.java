package com.spandigital.bbom.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends AbstractDto {

    private String username;

    private String encryptedPassword;

    private List<RoleDto> roles;
}
