package com.example.Final_Project_mutso.controller;


import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.service.CommentService;
import com.example.Final_Project_mutso.service.FeedService;
import com.example.Final_Project_mutso.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final CommentService commentService;
    private final FileService fileService;

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
            FeedDto dto,
            // add.html파일에서 가져온 파일 정보
            @RequestParam("files") MultipartFile file
    ){
        feedService.createFeed(dto, file);
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
                "file",
                fileService.readFile(feedId)

        );
        model.addAttribute(
                "commentList",
                commentService.readCommentAll(feedId));

        return "read";
    }

    @GetMapping("/{feedId}/updateView") // 정보 수정
    public String updateView(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            Model model
    ) {
        model.addAttribute(
                "feed",
                feedService.readFeed(feedId)
        );
        return "update";
    }

    @PostMapping("/{feedId}/update") // 정보 수정
    public String update(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            FeedDto feedDto,  // HTTP Request Body
            Model model
    ) {
        model.addAttribute(
                "feed",
                feedService.readFeed(feedId)
        );
        feedService.updateFeed(feedId, feedDto);
        return String.format("redirect:/feed/%s", feedId);
    }

    @GetMapping("/{feedId}/deleteView") // 정보 수정
    public String deketeView(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            Model model
    ) {
        model.addAttribute(
                "feed",
                feedService.readFeed(feedId)
        );
        return "delete";
    }

    @PostMapping("/{feedId}/delete")//삭제
    public String delete(
            @PathVariable("feedId") Long feedId
    ) {
        feedService.deleteFeed(feedId);
        return "redirect:/feed";
    }


}

