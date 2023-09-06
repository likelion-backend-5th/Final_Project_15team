package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String title;

    private String content;

    private String date;

    private String time;

    private String hashtag;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "feed_id", foreignKey = @ForeignKey(name = "FK_COMMENT_FEED"))
//    private Feed feed;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();
    private List<Comment> comments;

    @OneToMany
    private List<UserEntity> userScrap;

}
