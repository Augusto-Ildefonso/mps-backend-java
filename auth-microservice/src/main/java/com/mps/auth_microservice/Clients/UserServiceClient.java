package com.mps.auth_microservice.Clients;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.mps.auth_microservice.DTOs.UserCredentialsDTO;

@Service
public class UserServiceClient {

    private final RestClient restClient;

    public UserServiceClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public UserCredentialsDTO findByEmail(String email) {
        return restClient.get()
                .uri("/users/internal/by-email/{email}", email)
                .retrieve()
                .body(UserCredentialsDTO.class);
    }
}
