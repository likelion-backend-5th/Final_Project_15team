package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedHashtagRepository
        extends JpaRepository<FeedHashtag, Long> {

    List<FeedHashtag> findAllByFeed(Feed feed);
}
