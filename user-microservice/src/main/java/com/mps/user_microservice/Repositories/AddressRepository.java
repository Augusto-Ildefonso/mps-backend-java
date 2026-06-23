package com.mps.user_microservice.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.user_microservice.Entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
    List<AddressEntity> findByUserId(UUID userId);
}