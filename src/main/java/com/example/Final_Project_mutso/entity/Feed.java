package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTime;


//    private String hashtag;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedHashtag> feedHashtag;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedFile> file;


    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();
    private List<Comment> comments;

    @OneToMany
    private List<UserEntity> userScrap;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedLike> likes = new ArrayList<>();

    //좋아요
    public void addLikes(FeedLike like) {
        this.likes.add(like);
        like.setFeed(this);
    }

}
