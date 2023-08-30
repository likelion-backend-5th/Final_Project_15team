package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.MusicEntity;
import com.example.Final_Project_mutso.service.YoutubeVideoService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class YoutubeVideoDto {
    private String title;       // 음악
    private String imageUrlPath; // 음악 썸네일 url
    private String videoId; // 음악 식별 id
    private String artist; // 업로드 채널
    private String musicTime; // 음악 시간

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
