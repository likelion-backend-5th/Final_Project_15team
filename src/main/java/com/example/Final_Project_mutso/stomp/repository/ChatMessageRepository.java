package com.example.Final_Project_mutso.stomp.repository;

import com.example.Final_Project_mutso.stomp.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {

    List<ChatMessageEntity> findTop5ByRoomIdOrderByIdDesc(Long id);
}