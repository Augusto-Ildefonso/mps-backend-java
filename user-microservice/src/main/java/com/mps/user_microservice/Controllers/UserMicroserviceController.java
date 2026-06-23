package com.mps.user_microservice.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mps.user_microservice.DTOs.CreateUserRequest;
import com.mps.user_microservice.DTOs.ResponseUserDTO;
import com.mps.user_microservice.DTOs.UpdateUserRequest;
import com.mps.user_microservice.Entities.UserEntity;
import com.mps.user_microservice.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserMicroserviceController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ResponseUserDTO> create(@RequestBody CreateUserRequest request) {
        UserEntity user = userService.create(request);
        ResponseUserDTO response = new ResponseUserDTO(user.getCpf(), user.getEmail(), user.getName());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{cpf}")
    public ResponseEntity<ResponseUserDTO> update(@PathVariable String cpf, @RequestBody UpdateUserRequest request) {
        UserEntity user = userService.update(cpf, request);
        ResponseUserDTO response = new ResponseUserDTO(user.getCpf(), user.getEmail(), user.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseUserDTO> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(userService.findByCpf(cpf));
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        userService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

}
