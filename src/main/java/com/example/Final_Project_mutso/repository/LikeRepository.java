package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Likes;
import com.example.Final_Project_mutso.entity.PlayListShareFeed;
import com.example.Final_Project_mutso.entity.UserEntity;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByFeedAndUser(PlayListShareFeed feed , UserEntity user);

    Long countAllByFeed(PlayListShareFeed feed);
}
