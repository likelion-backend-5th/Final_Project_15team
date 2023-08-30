//package com.example.Final_Project_mutso.dto;
//
//import com.example.Final_Project_mutso.entity.MusicPlayList;
//import com.example.Final_Project_mutso.entity.PlayListCommentEntity;
//import com.example.Final_Project_mutso.entity.PlayListShareFeed;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Data
//public class PlayListRequestDto {
//    private String name;
//    private String writer;
//    private String description;
//    private MusicPlayList playList;
//    private List<Map> comments;
//
//    public static PlayListRequestDto fromEntity(PlayListShareFeed entity){
//        PlayListRequestDto dto = new PlayListRequestDto();
//
//        dto.setName(entity.getTitle());
//        dto.setWriter(entity.getUser().getUsername());
//        dto.setDescription(entity.getDescription());
//        dto.setPlayList(entity.getPlayList());
//        List<Map> commentsList = new ArrayList<>(); //comment 정보를 담기 위한 list
//        for(PlayListCommentEntity e : entity.getComments()){
//            Map<String,String> map = new HashMap<>();
//            map.put(e.getUser().getUsername(),e.getContent());
//            commentsList.add(map);
//        }
//        dto.setComments(commentsList);
//
//        return dto;
//    }
//}