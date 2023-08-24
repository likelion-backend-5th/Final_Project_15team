package com.example.Final_Project_mutso.dto;


import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Feed feed;
    private String content;

    public static CommentDto fromEntity(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setFeed(comment.getFeed());
        dto.setContent(comment.getContent());
        return dto;
    }
}
