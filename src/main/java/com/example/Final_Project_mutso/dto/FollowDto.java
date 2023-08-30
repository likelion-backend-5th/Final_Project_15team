package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class FollowDto {
    private List<String> followerList;
    private List<String> followingList;
    private String profileImage;
    private String nickname;

    public static FollowDto fromEntity(Follow entity, UserEntity userEntity) {
        FollowDto dto = new FollowDto();
        List<String> followerList = new ArrayList<>();
        for(UserEntity e : entity.getFollowerList())
            followerList.add(e.getUsername());

        List<String> followingList = new ArrayList<>();
        for(UserEntity e : entity.getFollowingList())
            followingList.add(e.getUsername());

        dto.setFollowerList(followerList);
        dto.setFollowingList(followingList);
        dto.setProfileImage(userEntity.getProfileImage());
        dto.setNickname(userEntity.getNickname());

        return dto;
    }

}
