package com.mps.user_microservice.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mps.user_microservice.DTOs.AddressRequest;
import com.mps.user_microservice.Entities.AddressEntity;
import com.mps.user_microservice.Entities.UserEntity;
import com.mps.user_microservice.Exceptions.UserNotFoundException;
import com.mps.user_microservice.Repositories.AddressRepository;
import com.mps.user_microservice.Repositories.UserRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public AddressEntity add(AddressRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        AddressEntity address = new AddressEntity();
        address.setName(request.getName());
        address.setCep(request.getCep());
        address.setStreet(request.getStreet());
        address.setNumber(request.getNumber());
        address.setNeighborhood(request.getNeighborhood());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setComplement(request.getComplement());
        address.setUser(user);

        return addressRepository.save(address);
    }

    public List<AddressEntity> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(authentication.getName());
        return addressRepository.findByUserId(userId);
    }
}
