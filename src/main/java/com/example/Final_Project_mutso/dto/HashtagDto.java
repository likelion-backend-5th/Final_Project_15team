package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Hashtag;
import lombok.Data;

@Data
public class HashtagDto {
    private String tagName;

    public static HashtagDto fromEntity(Hashtag hashtag) {
        HashtagDto hashtagDto = new HashtagDto();
        hashtagDto.setTagName(hashtagDto.getTagName());

        return hashtagDto;
    }
}
