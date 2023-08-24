package com.example.Final_Project_mutso.stomp.repository;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long> { }