package com.example.Final_Project_mutso.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserScrapDto {
    private List<String> scrapList;

//    public static ScrapDto fromEntity(Feed entity) {
//        ScrapDto dto = new ScrapDto();
//        List<String> scrapList = new ArrayList<>();
//        for(Feed e : entity.getScrapList())
//            scrapList.add(e.getUsername());
//
//        dto.setScrapList(scrapList);
//
//        return dto;
//    }
}
