package com.mps.auth_microservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mps.auth_microservice.Clients.UserServiceClient;
import com.mps.auth_microservice.DTOs.AuthRequestDTO;
import com.mps.auth_microservice.DTOs.AuthResponseDTO;
import com.mps.auth_microservice.DTOs.UserCredentialsDTO;
import com.mps.auth_microservice.Exceptions.UserNotFoundException;
import com.mps.auth_microservice.Providers.JwtProviderService;

@Service
public class AuthService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProviderService jwtProviderService;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        UserCredentialsDTO user;
        try {
            user = userServiceClient.findByEmail(request.getEmail());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Passwords don't match");
        }

        String token = jwtProviderService.createToken(user.getId());
        return new AuthResponseDTO(token, user.getName(), user.getEmail(), user.getCpf());
    }
}
