package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class FeedDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime dateTime;
    private List<FeedHashtagDto> hashtag;
    private List<CommentDto> comments;
    private String fileUrl;
    private String user;


    public static FeedDto fromEntity(Feed feed) {
        FeedDto feedDto = new FeedDto();
        feedDto.setUser(feed.getUser().getNickname());
        feedDto.setId(feed.getId());
        feedDto.setTitle(feed.getTitle());
        feedDto.setContent(feed.getContent());
        feedDto.setDateTime(feed.getDateTime());
        List<CommentDto> commentsList = new ArrayList<>(); //comment 정보를 담기 위한 list
        for(CommentDto e : feed.getComments()){
            commentsList.add(e);
        }
        feedDto.setComments(commentsList);

        List<FeedHashtagDto> hashtagList = new ArrayList<>(); //comment 정보를 담기 위한 list
        for(FeedHashtag e : feed.getFeedHashtag()){
            hashtagList.add(FeedHashtagDto.fromEntity(e));
        }
        feedDto.setHashtag(hashtagList);

        feedDto.setFileUrl(feedDto.getFileUrl());

//        feedDto.setUser(feed.getUser());


        return feedDto;
    }
}

