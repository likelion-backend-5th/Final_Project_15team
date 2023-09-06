package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedImageRepository
        extends JpaRepository<FeedImage, Long> {
    List<FeedImage> findAllByFeedId(Long id);
}
