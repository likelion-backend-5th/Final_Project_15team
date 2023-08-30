package com.example.Final_Project_mutso.dto;


import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Feed feed;
//    private UserEntity user;
    private String content;

    public static CommentDto fromEntity(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setFeed(comment.getFeed());
//        dto.setUser(comment.getUser());
        dto.setContent(comment.getContent());
        return dto;
    }
}
