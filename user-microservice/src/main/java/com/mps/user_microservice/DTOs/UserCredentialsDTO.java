package com.mps.user_microservice.DTOs;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialsDTO {
    private UUID id;
    private String password;
    private String name;
    private String email;
    private String cpf;
}
