package com.mps.user_microservice.DTOs;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String cpf;
    private String name;
    private String email;
    private String password;
}
