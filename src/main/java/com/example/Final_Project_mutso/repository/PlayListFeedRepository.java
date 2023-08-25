package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.PlayListShareFeed;
import com.example.Final_Project_mutso.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListFeedRepository extends JpaRepository<PlayListShareFeed,Long> {
    Page<PlayListShareFeed> findAllByUser(UserEntity user, Pageable pageable);
}
