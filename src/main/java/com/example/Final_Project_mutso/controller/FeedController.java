package com.example.Final_Project_mutso.controller;


import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.dto.FeedListDto;
import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.FeedRepository;
import com.example.Final_Project_mutso.service.CommentService;
import com.example.Final_Project_mutso.service.FeedService;
import com.example.Final_Project_mutso.service.FileService;
import com.example.Final_Project_mutso.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


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
    private final UserService userService;
    private final FeedRepository feedRepository;

    @GetMapping
    public List<FeedListDto> getFeed() {
        return feedService.readFeedAll();
    }

    @PostMapping(value = "/add",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//피드 생성
    // RESTful한 API는 행동의 결과로 반영된 자원의 상태를 반환함이 옳다
    public void create(
            @RequestPart(value = "dto") FeedDto dto,
            @RequestPart(value = "file") MultipartFile file,
            Authentication authentication
    ){
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        UserEntity loginedUser = userService.readUser(userDetails.getName());
        feedService.createFeed(dto, file/*, loginedUser*/);
    }



    @GetMapping("/{feedId}") //피드 상세
    public FeedDto read(
            @PathVariable("feedId") Long feedId)
    {
        return feedService.readFeed(feedId);
//        commentService.readCommentPaged(feedId, 0, 5);
//        fileService.readFile(feedId);

    }


    @PutMapping("/{feedId}") // 정보 수정
    public void update(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            @RequestPart("dto") FeedDto dto,  // HTTP Request Body
            @RequestPart("file") MultipartFile file,
            Authentication authentication
    ) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        UserEntity loginedUser = userService.readUser(userDetails.getName());
//        Feed feed = feedRepository.findByFeedId(feedId);
//        if(loginedUser.equals(feed.getUser()))
        feedService.updateFeed(feedId, dto);
        fileService.updateFile(feedId, file);
    }



    @DeleteMapping("/{feedId}")//삭제
    public void delete(
            @PathVariable("feedId") Long feedId,
            Authentication authentication
    ) {
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        UserEntity loginedUser = userService.readUser(userDetails.getName());
//        Feed feed = feedRepository.findByFeedId(feedId);
//        if(loginedUser.equals(feed.getUser()))
        fileService.deleteFile(feedId);
        feedService.deleteFeed(feedId);
//        return "redirect:/feed";
    }

    @PostMapping("/{feedId}/like")  // 경로 변수명을 boardId로 변경
    public ResponseEntity<String> likeFeed(
            @PathVariable("feedId") Long feedId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity loginedUser = userService.readUser(userDetails.getName());
        feedService.likeFeed(feedId,loginedUser);
        return ResponseEntity.ok("좋아요가 반영되었습니다.");
    }

    @PostMapping("/{feedId}/unlike")
    public ResponseEntity<String> unlikeFeed(
            @PathVariable("feedId") Long feedId,
            Authentication authentication
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity loginedUser = userService.readUser(userDetails.getName());
        feedService.unlikeFeed(feedId, loginedUser);
        return ResponseEntity.ok("좋아요가 취소되었습니다.");
    }

}

