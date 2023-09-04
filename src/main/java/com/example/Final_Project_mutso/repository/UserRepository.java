package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    UserEntity findAllByUsername(String username);
}
