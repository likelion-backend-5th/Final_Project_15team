package com.example.Final_Project_mutso.controller;


import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.dto.FeedListDto;
import com.example.Final_Project_mutso.entity.CustomUserDetails;
import com.example.Final_Project_mutso.entity.UserEntity;
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

    @GetMapping
    public List<FeedListDto> getFeed() {
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


    @PutMapping("/{feedId}") // 정보 수정
    public void update(
            @PathVariable("feedId") Long feedId,  // URL의 ID
            FeedDto feedDto,  // HTTP Request Body
            @RequestParam("file") MultipartFile file
    ) {
        feedService.updateFeed(feedId, feedDto);
        fileService.updateFile(feedId, file);
    }



    @DeleteMapping("/{feedId}")//삭제
    public void delete(
            @PathVariable("feedId") Long feedId
    ) {
        fileService.deleteFile(feedId);
        feedService.deleteFeed(feedId);
//        return "redirect:/feed";
    }

    @PostMapping("/{feedId}/like")
    public ResponseEntity<String> likeFeed(
            @PathVariable("feedId") Long feedId,
            Authentication authentication
    ) {  // 매개변수명을 boardId로 변경
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity loginedUser = userService.readUser(userDetails.getName());
        myActivityService.likeBoard(loginedUser.getId(), boardId);
        return ResponseEntity.ok("좋아요가 반영되었습니다.");
    }

}

