package com.example.Final_Project_mutso.stomp.repository;

import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findTop5ByRoomIdOrderByIdDesc(Long id);
}