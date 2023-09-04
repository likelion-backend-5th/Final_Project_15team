package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<MusicEntity,Long> {
}
