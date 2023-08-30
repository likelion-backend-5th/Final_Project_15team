package com.example.Final_Project_mutso.repository;


import com.example.Final_Project_mutso.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
    // CommentEntity 중 articleId가
    // id인 CommentEntity 만 반환하는 메소드
    List<Comment> findAllByFeedId(Long id);
}
