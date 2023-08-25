package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.entity.PlayListShareFeed;
import lombok.Data;

import java.util.List;

@Data
public class PlayListRequestDto {
    private String name;
    private String writer;
    private String description;
    private MusicPlayList playList;

    public static PlayListRequestDto fromEntity(PlayListShareFeed entity){
        PlayListRequestDto dto = new PlayListRequestDto();

        dto.setName(entity.getTitle());
        dto.setWriter(entity.getUser().getUsername());
        dto.setDescription(entity.getDescription());
        dto.setPlayList(entity.getPlayList());

        return dto;
    }
}
