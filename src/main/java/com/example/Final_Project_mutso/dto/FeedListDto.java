package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedImage;
import com.example.Final_Project_mutso.entity.FeedVideo;
import lombok.Data;

import java.util.List;

@Data
public class FeedListDto {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String time;
    private String hashtag;
    private String fileUrl;
//    private List<FeedImage> image;
//    private List<FeedVideo> video;
//    private UserEntity user;


    public static FeedListDto fromEntity(Feed feed) {
        FeedListDto feedDto = new FeedListDto();
        feedDto.setId(feed.getId());
        feedDto.setTitle(feed.getTitle());
        feedDto.setContent(feed.getContent());
//        feedDto.setHashtag(feed.getHashtag());
        feedDto.setFileUrl(feedDto.getFileUrl());
//        feedDto.setImage(feed.getImage());
//        feedDto.setVideo(feed.getVideo());
//        feedDto.setComments(feed.getComments());
//        feedDto.setUser(feed.getUser());


        return feedDto;
    }
}

