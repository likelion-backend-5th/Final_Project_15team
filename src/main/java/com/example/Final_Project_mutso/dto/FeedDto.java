package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedImage;
import com.example.Final_Project_mutso.entity.FeedVideo;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class FeedDto {
    private Long id;
    private String title;
    private String content;
    private String date;
    private String time;
    private String hashtag;
//    private String file;
    private List<CommentDto> comments;
    private String fileUrl;
//    private List<FeedImage> image;
//    private List<FeedVideo> video;
//    private UserEntity user;


    public static FeedDto fromEntity(Feed feed) {
        FeedDto feedDto = new FeedDto();
        feedDto.setId(feed.getId());
        feedDto.setTitle(feed.getTitle());
        feedDto.setContent(feed.getContent());
        feedDto.setHashtag(feed.getHashtag());
//        feedDto.setImage(feed.getImage());
//        feedDto.setVideo(feed.getVideo());
        List<CommentDto> commentsList = new ArrayList<>(); //comment 정보를 담기 위한 list
        for(CommentDto e : feed.getComments()){
            commentsList.add(e);
        }
        feedDto.setComments(commentsList);

//        feedDto.setFileUrl(feed.getImage().toString());

//        feedDto.setFileUrl(feedDto.getFileUrl());

//        feedDto.setComments();
//        feedDto.setUser(feed.getUser());


        return feedDto;
    }
}

