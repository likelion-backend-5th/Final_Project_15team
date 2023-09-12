package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository
        extends JpaRepository<FeedLike, Long> {
//    @Override
    Optional<FeedLike> findByUserId(Long id);

    int countFeedLikeByFeed_Id(Long id);

}
