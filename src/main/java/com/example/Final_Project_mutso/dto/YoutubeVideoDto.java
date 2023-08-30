package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.MusicEntity;
import com.example.Final_Project_mutso.service.YoutubeVideoService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeVideoDto {
    private String title;       // 동영상 제목
    private String imageUrlPath; // 동영상 썸네일 url
    private String videoId; // 동영상 식별 id
    private String artist;
    private String musicTime;

    public static YoutubeVideoDto fromEntity(MusicEntity entity){
        YoutubeVideoDto dto = new YoutubeVideoDto();
        dto.setArtist(entity.getArtist());
        dto.setVideoId(entity.getMusicId());
        dto.setImageUrlPath(entity.getImageUrl());
        dto.setTitle(entity.getMusicName());
        dto.setMusicTime(entity.getMusicTime());
        return dto;
    }


}
