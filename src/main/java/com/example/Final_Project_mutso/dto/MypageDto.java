package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Follow;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MypageDto {
    private String username;
    private String name;
    private List<String> followerList;
    private List<String> followingList;


    public static MypageDto fromEntity(UserEntity entity, Follow follow) {
        MypageDto dto = new MypageDto();
        List<String> followerList = new ArrayList<>();
        for(UserEntity e : follow.getFollowerList())
            followerList.add(e.getUsername());

        List<String> followingList = new ArrayList<>();
        for(UserEntity e : follow.getFollowingList())
            followingList.add(e.getUsername());

        dto.setFollowerList(followerList);
        dto.setFollowingList(followingList);
        dto.setUsername(entity.getUsername());
        dto.setName(entity.getName());

//        dto.setFollowerList(follow.getFollowerList());
//        dto.setFollowingList(follow.getFollowingList());

        return dto;
    }
}
