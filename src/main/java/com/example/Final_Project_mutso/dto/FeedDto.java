package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import lombok.Data;

import java.util.List;

@Data
public class FeedDto {
    private String title;
    private String content;
    private String date;
    private String time;
    private String hashtag;
    private List<Comment> comments;

//    private String image_url;
//    private UserEntity user;


    public static FeedDto fromEntity(Feed feed) {
        FeedDto feedDto = new FeedDto();
        feedDto.setTitle(feed.getTitle());
        feedDto.setContent(feed.getContent());
        feedDto.setHashtag(feed.getHashtag());
//        feedDto.setImage_url(feed.getImage_url());
//        feedDto.setUser(feed.getUser());

        return feedDto;
    }
}

