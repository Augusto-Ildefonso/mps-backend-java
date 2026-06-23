package com.mps.user_microservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseUserDTO {
    private String cpf;
    private String email;
    private String name;
}
