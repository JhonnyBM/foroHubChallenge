package com.alura.foroHub.jwtAuth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
