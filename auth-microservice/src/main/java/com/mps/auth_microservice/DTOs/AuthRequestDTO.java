package com.mps.auth_microservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDTO {
    private String email;
    private String password;
}
