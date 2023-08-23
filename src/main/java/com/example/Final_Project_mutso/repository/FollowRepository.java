package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Follow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // unFolow
    @Transactional
    int deleteByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    // 팔로우 유무
    int countByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    // 팔로우 리스트
    List<Follow> findByFromUserId(Long fromUserId);

    // 팔로워 리스트
    List<Follow> findByToUserId(Long toUser);

}

