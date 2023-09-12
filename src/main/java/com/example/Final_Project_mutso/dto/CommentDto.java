package com.example.Final_Project_mutso.dto;


import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.UserEntity;
import lombok.Data;

@Data
public class CommentDto {
//    private UserEntity user;
    private Long id;
    private String nickname;
    private String content;

    public static CommentDto fromEntity(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setNickname(comment.getUser().getNickname());
        dto.setContent(comment.getContent());
        return dto;
    }
}
