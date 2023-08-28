package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {
    private Long id;
    private String roomName;

    public static ChatRoomDto fromEntity(ChattingRoom entity) {
        return new ChatRoomDto(
                entity.getId(),
                entity.getRoomName()
        );
    }
}
