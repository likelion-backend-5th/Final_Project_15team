package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.FeedImage;
import com.example.Final_Project_mutso.entity.FeedVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedVideoRepository
        extends JpaRepository<FeedVideo, Long> {
    List<FeedVideo> findAllByFeedId(Long id);
}
