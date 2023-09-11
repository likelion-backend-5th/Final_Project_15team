package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FeedListDto {
    private Long id;
    private String title;
    private String content;
    private List<FeedHashtagDto> hashtag;
    private String fileUrl;


    public static FeedListDto fromEntity(Feed feed) {
        FeedListDto feedDto = new FeedListDto();
        feedDto.setId(feed.getId());
        feedDto.setTitle(feed.getTitle());
        feedDto.setContent(feed.getContent());
        feedDto.setFileUrl(feedDto.getFileUrl());
        List<FeedHashtagDto> hashtagList = new ArrayList<>(); //comment 정보를 담기 위한 list
        for(FeedHashtag e : feed.getFeedHashtag()){
            hashtagList.add(FeedHashtagDto.fromEntity(e));
        }
        feedDto.setHashtag(hashtagList);

        return feedDto;
    }
}

