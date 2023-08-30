package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

@Data
public class MypageDto {
    private String username;

    public static MypageDto fromEntity(UserEntity entity) {
        MypageDto dto = new MypageDto();
        dto.setUsername(entity.getUsername());

        return dto;
    }
}
