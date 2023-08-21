package com.example.Final_Project_mutso.dto;


import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.User;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Feed feed;
    private User user;
    private String content;

    public static CommentDto fromEntity(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setFeed(comment.getFeed());
        dto.setUser(comment.getUser());
        dto.setContent(comment.getContent());
        return dto;
    }
}
