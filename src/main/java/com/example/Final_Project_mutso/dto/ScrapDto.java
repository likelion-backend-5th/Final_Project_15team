package com.example.Final_Project_mutso.dto;

import lombok.Data;

import java.util.List;

@Data
public class ScrapDto {
    private List<String> scrapList;

    private String title;

//    public static ScrapDto fromEntity(Feed entity) {
//        ScrapDto dto = new ScrapDto();
//        List<String> scrapList = new ArrayList<>();
//        for(Feed e : entity.getScrapList())
//            scrapList.add(e.getUserId());
//
//        dto.setScrapList(scrapList);
//        dto.setTitle(entity.getTitle);
//
//        return dto;
//    }

}
