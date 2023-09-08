package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.stomp.entity.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK, EXIT, FILE
    }
    private MessageType type; //메시지 타입
    private Long roomId;
    private String sender;
    private String message;
    private String time;
    private String fileUrl;

    public static ChatMessageDto fromEntity(ChatMessage entity){
        ChatMessageDto dto = new ChatMessageDto();
        dto.setFileUrl(entity.getFileUrl());
        return dto;
    }
}