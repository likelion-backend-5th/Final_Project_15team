package com.example.Final_Project_mutso.controller;


import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.service.CommentService;
import com.example.Final_Project_mutso.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final CommentService commentService;

    @GetMapping
    public String getFeed(Model model) {
        model.addAttribute(
                "feedList",
                feedService.readFeedAll()
        );
        return "feedList";
    }

    @GetMapping("/addView")
    public String addView() {
        return "add";
    }

    @PostMapping("/add")//피드 생성
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환함이 옳다
    public String create(
            FeedDto dto
    ){
        feedService.createFeed(dto);
        return "redirect:/feed";
    }



    @GetMapping("/{feedId}") //피드 상세
    public String read(
            @PathVariable("feedId") Long feedId,
            Model model)
    {
        model.addAttribute(
                "feed",
                feedService.readFeed(feedId)
        );
        model.addAttribute(
                "commentList",
                commentService.readCommentAll(feedId));

        return "read";
    }



    @PutMapping("/{feedId}/update") // 정보 수정
    public void update(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            @RequestBody FeedDto feedDto  // HTTP Request Body
    ) {
        feedService.updateFeed(feedId, feedDto);
    }


    @DeleteMapping("/{feedId}/delete")//삭제
    public void delete(
            @PathVariable("feedId") Long feedId,
            @RequestBody FeedDto dto
    ) {
        feedService.deleteFeed(feedId, dto);
    }


}

