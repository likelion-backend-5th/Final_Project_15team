package com.example.Final_Project_mutso.entity;

import com.example.Final_Project_mutso.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data

public class Comment extends CommentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;

    private String content;

    private String time;

}
