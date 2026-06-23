package com.mps.user_microservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mps.user_microservice.DTOs.CreateUserRequest;
import com.mps.user_microservice.DTOs.ResponseUserDTO;
import com.mps.user_microservice.DTOs.UpdateUserRequest;
import com.mps.user_microservice.Entities.UserEntity;
import com.mps.user_microservice.Exceptions.UserFoundException;
import com.mps.user_microservice.Exceptions.UserNotFoundException;
import com.mps.user_microservice.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity create(CreateUserRequest request) {
        userRepository.findByCpf(request.getCpf()).ifPresent(user -> {
            throw new UserFoundException();
        });

        UserEntity user = new UserEntity();
        user.setCpf(request.getCpf());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public UserEntity update(String cpf, UpdateUserRequest request) {
        UserEntity user = userRepository.findByCpf(cpf)
                .orElseThrow(UserNotFoundException::new);

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.saveAndFlush(user);
    }

    public ResponseUserDTO findByCpf(String cpf) {
        UserEntity user = userRepository.findByCpf(cpf)
                .orElseThrow(UserNotFoundException::new);
        return new ResponseUserDTO(user.getCpf(), user.getEmail(), user.getName());
    }

    public void delete(String cpf) {
        UserEntity user = userRepository.findByCpf(cpf)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }
}
