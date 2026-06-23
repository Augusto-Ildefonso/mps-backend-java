package com.mps.user_microservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mps.user_microservice.DTOs.UserCredentialsDTO;
import com.mps.user_microservice.Entities.UserEntity;
import com.mps.user_microservice.Exceptions.UserNotFoundException;
import com.mps.user_microservice.Repositories.UserRepository;

@RestController
@RequestMapping("/users/internal")
public class InternalUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserCredentialsDTO> findByEmail(@PathVariable String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        UserCredentialsDTO response = new UserCredentialsDTO(user.getId(), user.getPassword(), user.getName(), user.getEmail(), user.getCpf());
        return ResponseEntity.ok(response);
    }
}
