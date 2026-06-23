package com.mps.user_microservice.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mps.user_microservice.DTOs.AddressRequest;
import com.mps.user_microservice.Entities.AddressEntity;
import com.mps.user_microservice.Services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<AddressEntity> add(@RequestBody AddressRequest request) {
        AddressEntity address = addressService.add(request);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AddressEntity>> list() {
        return ResponseEntity.ok(addressService.list());
    }

}
