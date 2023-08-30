package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class MypageDto {
    private String username;
    private String name;
    private List<UserEntity> followerList;
    private List<UserEntity> followingList;


    public static MypageDto fromEntity(UserEntity entity, Follow follow) {
        MypageDto dto = new MypageDto();
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());
        dto.setFollowerList(follow.getFollowerList());
        dto.setFollowingList(follow.getFollowingList());

        return dto;
    }
}
