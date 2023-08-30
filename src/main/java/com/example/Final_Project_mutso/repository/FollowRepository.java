package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUser(UserEntity user);

}

