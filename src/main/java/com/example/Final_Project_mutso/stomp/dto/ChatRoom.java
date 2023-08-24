package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    private Long id;
    private String roomName;

    public static ChatRoom fromEntity(ChattingRoom entity) {
        return new ChatRoom(
                entity.getId(),
                entity.getRoomName()
        );
    }
}
