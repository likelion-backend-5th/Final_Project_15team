package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

@Data
public class ProfileDto {
    private String username;
    private String profileImage;
    private String nickname;
    private String introduction;

    public static ProfileDto fromEntity(UserEntity entity) {
        ProfileDto dto = new ProfileDto();
        dto.setUsername(entity.getUsername());
        dto.setProfileImage(entity.getProfileImage());
        dto.setNickname(entity.getNickname());
        dto.setIntroduction(entity.getIntroduction());

        return dto;
    }
}
