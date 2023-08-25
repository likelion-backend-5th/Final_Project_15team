package com.example.Final_Project_mutso.stomp.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long roomId;
    private String sender;
    private String message;
    private String time;
}