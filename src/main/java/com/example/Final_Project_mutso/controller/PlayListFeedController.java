package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.dto.PlayListFeedDto;
import com.example.Final_Project_mutso.dto.PlayListRequestDto;
import com.example.Final_Project_mutso.service.PlayListFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/playlistfeed")
@RequiredArgsConstructor
public class PlayListFeedController {
    private final PlayListFeedService service;

    @PutMapping
    public void writeFeed(
            @RequestParam(value="title") String title,
            @RequestParam(value="description") String description,
            @RequestParam(value="index") int idx
    ){
        service.writeFeed(title,description,idx);
    }

    @GetMapping("/myfeed")
    public Page<PlayListRequestDto> readArticles(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer limit
    ){
        return service.readItemPaged(page,limit);
    }
}
