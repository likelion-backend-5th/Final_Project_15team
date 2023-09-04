package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {
    private Long id;
    private String roomName;
    private int userCount;
    private String imageUrl;

    public static ChatRoomDto fromEntity(ChattingRoom entity) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.setId(entity.getId());
        dto.setRoomName(entity.getRoomName());
        dto.setUserCount(entity.getUserCount());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }
}
