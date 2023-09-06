package com.example.Final_Project_mutso.dto;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.Scrap;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScrapDto {
    private List<Long> scrapList;
    private String title;

    public static ScrapDto fromEntity(Scrap entity, Feed feed) {
        ScrapDto dto = new ScrapDto();
        List<Long> scrapList = new ArrayList<>();
        for(Feed e : entity.getScrapList())
            scrapList.add(e.getId());

        dto.setScrapList(scrapList);
        dto.setTitle(feed.getTitle());

        return dto;
    }

}
