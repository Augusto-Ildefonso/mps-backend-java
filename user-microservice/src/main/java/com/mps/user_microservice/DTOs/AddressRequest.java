package com.mps.user_microservice.DTOs;

import lombok.Data;

@Data
public class AddressRequest {
    private String name;
    private String cep;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String state;
    private String complement;
}
