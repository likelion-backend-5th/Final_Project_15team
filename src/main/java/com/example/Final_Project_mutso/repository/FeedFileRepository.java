package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.FeedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedFileRepository
        extends JpaRepository<FeedFile, Long> {
    List<FeedFile> findAllByFeedId(Long id);
}
