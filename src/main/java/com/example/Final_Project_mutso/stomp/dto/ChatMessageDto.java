package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.stomp.jpa.ChatMessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long roomId;
    private String sender;
    private String message;
    private String time;


    public static ChatMessageDto fromEntity(ChatMessageEntity messageEntity) {
        return new ChatMessageDto(
                messageEntity.getRoomId(),
                messageEntity.getSender(),
                messageEntity.getMessage(),
                messageEntity.getTime()
        );
    }

    public ChatMessageEntity newEntity() {
        ChatMessageEntity messageEntity = new ChatMessageEntity();
        messageEntity.setRoomId(roomId);
        messageEntity.setSender(sender);
        messageEntity.setMessage(message);
        messageEntity.setTime(time);
        return messageEntity;
    }
}