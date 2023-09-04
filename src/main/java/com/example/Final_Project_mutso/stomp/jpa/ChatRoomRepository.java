package com.example.Final_Project_mutso.stomp.jpa;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChattingRoom, Long> {
//    Optional<ChattingRoom> findByRoomId(String roomId);
    Optional<ChattingRoom> findById(Long id);
}
