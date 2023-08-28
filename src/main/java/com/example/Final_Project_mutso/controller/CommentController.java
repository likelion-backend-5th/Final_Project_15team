package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.dto.CommentDto;
import com.example.Final_Project_mutso.service.CommentService;
import com.example.Final_Project_mutso.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@Slf4j
@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
// cors 설정
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;
    private final FeedService feedService;

    @GetMapping("/{feedId}") //해당 피드 댓글 모두 불러오기
    public List<CommentDto> readAll(
            @PathVariable("feedId") Long feedId
    ) {
        return commentService.readCommentAll(feedId);
    }

    @GetMapping("/{feedId}/list") //댓글 리스트
    public Page<CommentDto> readFeedCommments(
            @PathVariable("feedId") Long feedId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer limit
    ){
        return commentService.readCommentPaged(feedId,page,limit);
    }

    @PostMapping("/{feedId}") //댓글 등록
    public void createComment(
            @PathVariable("feedId") Long feedId,
            CommentDto commentDto)
    {
        commentService.createComment(feedId, commentDto);
    }

    @PutMapping("/{feedId}/{commentId}")
    public void updateComment(
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId,
            CommentDto commentDto
    ){
        commentService.updateComment(feedId, commentId, commentDto);
    }

    @DeleteMapping("/{feedId}/{commentId}")
    public void deleteComment(
            @PathVariable("feedId") Long feedId,
            @PathVariable("commentId") Long commentId
    ){
        commentService.deleteComment(feedId, commentId);
    }

//    @PostMapping("/read")
//    public String commentWrite(
//            @PathVariable("id") Long id,
//            @RequestParam("comment") String comment) throws Exception {
//        commentService.createComment(comment);
//        BoardDTO boardDTO = new BoardDTO(id);
//        return String.format("redirect:/%s", id);
//    }
//    @GetMapping("/{id}")
//    public String comment(
//            @PathVariable("id") Long id,
//            Model model) {
//        model.addAttribute(
//                "commentList",
//                commentService.readComment(id)
//        );
//        return "read";
//    }
}
