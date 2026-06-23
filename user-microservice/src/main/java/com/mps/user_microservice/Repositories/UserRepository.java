package com.mps.user_microservice.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mps.user_microservice.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByCpf(String cpf);
    Optional<UserEntity> findByEmail(String email);
}
