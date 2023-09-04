package com.example.Final_Project_mutso.stomp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK, EXIT
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MessageType type;
    private Long roomId;
    private String sender;
    private String message;
    private String time;
    private String profile;
    private String fileUrl;
}
