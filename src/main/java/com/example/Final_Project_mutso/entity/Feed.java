package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Data
//public class Feed {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
//
//    private String title;
//
//    private String content;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    private LocalDateTime dateTime;
//
//
////    private String hashtag;
//
//    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
//    private List<FeedHashtag> feedHashtag;
//
//    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
//    private List<FeedFile> file;
//
//
//    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
////    private List<Comment> comments = new ArrayList<>();
//    private List<Comment> comments;
//
//    @ManyToMany
//    private List<UserEntity> userScrap;
//
//    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FeedLike> likes = new ArrayList<>();
//
//    //좋아요
//    public void addLikes(FeedLike like) {
//        this.likes.add(like);
//        like.setFeed(this);
//    }
//
//}
@Entity
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

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedHashtag> feedHashtag;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<FeedFile> file;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany
    private List<UserEntity> userScrap;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedLike> likes = new ArrayList<>();

    // Getter와 Setter 메서드 구현
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<FeedHashtag> getFeedHashtag() {
        return feedHashtag;
    }

    public void setFeedHashtag(List<FeedHashtag> feedHashtag) {
        this.feedHashtag = feedHashtag;
    }

    public List<FeedFile> getFile() {
        return file;
    }

    public void setFile(List<FeedFile> file) {
        this.file = file;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<UserEntity> getUserScrap() {
        return userScrap;
    }

    public void setUserScrap(List<UserEntity> userScrap) {
        this.userScrap = userScrap;
    }

    public List<FeedLike> getLikes() {
        return likes;
    }

    public void setLikes(List<FeedLike> likes) {
        this.likes = likes;
    }

    //좋아요
    public void addLikes(FeedLike like) {
        this.likes.add(like);
        like.setFeed(this);
    }

}

