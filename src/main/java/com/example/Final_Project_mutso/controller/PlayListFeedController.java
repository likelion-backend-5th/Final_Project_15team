package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.dto.PlayListRequestDto;
import com.example.Final_Project_mutso.service.PLCommentService;
import com.example.Final_Project_mutso.service.PlayListFeedService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/playlistfeed")
@RequiredArgsConstructor
public class PlayListFeedController {
    private final PlayListFeedService service;
    private final PLCommentService plCommentService;

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

    @GetMapping("/{feedId}")
    public PlayListRequestDto readArticle(
            @PathVariable(value = "feedId") Long feedId
    ){
        return service.readFeed(feedId);
    }

    @PutMapping("/{feedId}/comment")
    public void postComment(
            @PathVariable(value = "feedId") Long feedId,
            @RequestParam(value = "content") String content
    ){
        plCommentService.post(feedId,content);
    }
}
