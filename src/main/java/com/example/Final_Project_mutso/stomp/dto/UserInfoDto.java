package com.example.Final_Project_mutso.stomp.dto;

import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

@Data
public class UserInfoDto {
    private String username;
    private String nickname;
    private String profileImage;

    public static UserInfoDto fromEntity(UserEntity entity){
        UserInfoDto dto = new UserInfoDto();
        dto.setUsername(entity.getUsername());
        dto.setNickname(entity.getNickname());
        dto.setProfileImage(entity.getProfileImage());
        return dto;
    }
}
