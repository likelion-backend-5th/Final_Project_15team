package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.service.FeedLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
// cors 설정
@CrossOrigin(origins = "*")

public class FeedLikeController {

    private final FeedLikeService feedLikeService;

    @PostMapping("/{feedId}/like")// 좋아요 클릭
    public String likeFeed(
            @PathVariable("feedId") Long feedId,
            Authentication authentication
    ) {
        return feedLikeService.likeFeed(feedId);
    }

    @GetMapping("/{feedId}/like")// 좋아요 개수
    public int likeCnt(
            @PathVariable("feedId") Long feedId
    ) {
        return feedLikeService.getCntFeedLikes(feedId);
    }

    @GetMapping("/{feedId}/likeUsers")// 좋아요한 유저 목록
    public List<String> likeUsers(
            @PathVariable("feedId") Long feedId
    ) {
        return feedLikeService.getFeedLikeUsers(feedId);
    }
}
