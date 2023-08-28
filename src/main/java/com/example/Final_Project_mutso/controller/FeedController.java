package com.example.Final_Project_mutso.controller;


import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.service.CommentService;
import com.example.Final_Project_mutso.service.FeedService;
import com.example.Final_Project_mutso.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
// cors 설정
@CrossOrigin(origins = "*")
public class FeedController {

    private final FeedService feedService;
    private final CommentService commentService;
    private final FileService fileService;

    @GetMapping
    public List<Feed> getFeed() {
        return feedService.readFeedAll();
    }

    @PostMapping(value = "/add",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//피드 생성
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환함이 옳다
    public void create(
            FeedDto dto,
            @RequestParam("file") MultipartFile file
    ){
        feedService.createFeed(dto, file);
    }



    @GetMapping("/{feedId}") //피드 상세
    public FeedDto read(
            @PathVariable("feedId") Long feedId)
    {
        return feedService.readFeed(feedId);
//        commentService.readCommentPaged(feedId, 0, 5);
//        fileService.readFile(feedId);

    }


    @PutMapping("/{feedId}/update") // 정보 수정
    public void update(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            FeedDto feedDto,  // HTTP Request Body
            @RequestParam("file") MultipartFile file
    ) {
        feedService.updateFeed(feedId, feedDto);
        fileService.updateFile(feedId, file);
    }



    @DeleteMapping("/{feedId}/delete")//삭제
    public void delete(
            @PathVariable("feedId") Long feedId
    ) {
        fileService.deleteFile(feedId);
        feedService.deleteFeed(feedId);
//        return "redirect:/feed";
    }


}

