package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.FeedHashtag;
import lombok.Data;

@Data
public class FeedHashtagDto {
    private String tagName;

    public static FeedHashtagDto fromEntity(FeedHashtag hashtag) {
        FeedHashtagDto hashtagDto = new FeedHashtagDto();
        hashtagDto.setTagName(hashtag.getHashtag().getTagName());

        return hashtagDto;
    }
}
