package com.ismailjacoby.djbusinessappapi.dto;

import com.ismailjacoby.djbusinessappapi.model.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class AuthDTO {
    private String username;
    private String token;
    private UserRole role;
}
