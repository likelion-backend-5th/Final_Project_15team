package com.example.Final_Project_mutso.stomp.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat_room")
@Data
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;
}