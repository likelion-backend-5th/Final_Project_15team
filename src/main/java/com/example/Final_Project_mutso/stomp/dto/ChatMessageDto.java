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
        ENTER, TALK, EXIT
    }
    private MessageType type; //메시지 타입
    private Long roomId;
    private String sender;
    private String message;
    private String time;
    private String profile;
    private String fileUrl;


//    public static ChatMessageDto fromEntity(ChatMessage messageEntity) {
//        return new ChatMessageDto(
//                messageEntity.getRoomId(),
//                messageEntity.getSender(),
//                messageEntity.getMessage(),
//                messageEntity.getTime(),
//                messageEntity.getProfile(),
//                messageEntity.getFileUrl()
//        );
//    }
//
//    public ChatMessage newEntity() {
//        ChatMessage messageEntity = new ChatMessage();
//        messageEntity.setRoomId(roomId);
//        messageEntity.setSender(sender);
//        messageEntity.setMessage(message);
//        messageEntity.setTime(time);
//        messageEntity.setProfile(profile);
//        messageEntity.setFileUrl(fileUrl);
//        return messageEntity;
//    }
}