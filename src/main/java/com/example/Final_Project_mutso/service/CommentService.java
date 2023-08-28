package com.example.Final_Project_mutso.service;


import com.example.Final_Project_mutso.dto.CommentDto;
import com.example.Final_Project_mutso.dto.ResponseDto;
import com.example.Final_Project_mutso.entity.Comment;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.repository.CommentRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;


    public Comment createComment(Long feedId, CommentDto dto) {
        // articleId를 ID로 가진 ArticleEntity 가 존재 하는지?
        if (!feedRepository.existsById(feedId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);  // 자유롭게 상황대처

        Optional<Feed> optionalFeed
                = feedRepository.findById(feedId);
        Feed feed = optionalFeed.get();
        Comment comment = new Comment();
        comment.setFeed(feed);
        comment.setContent(dto.getContent());
        return commentRepository.save(comment);

    }


    public Page<CommentDto> readCommentPaged(
            Long feedId, Integer pageNumber, Integer pageSize
    ) {
        Optional<Comment> optionalComment
                = commentRepository.findById(feedId);
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Comment comment = optionalComment.get();

        if (!feedId.equals(comment.getFeed().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by("id").descending());
        Page<Comment> commentEntityPage
                = commentRepository.findAll(pageable);
        Page<CommentDto> commentDtoPage
                = commentEntityPage.map(CommentDto::fromEntity);
        return commentDtoPage;
    }

    public ResponseDto updateComment(
            Long feedId,
            Long commentId,
            CommentDto dto
    ) {
        // 요청한 댓글이 존재하는지
        Optional<Comment> optionalComment
                = commentRepository.findById(commentId);
        // 존재하지 않으면 예외 발생
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 아니면 로직 진행
        Comment comment = optionalComment.get();

        // 대상 댓글이 대상 게시글의 댓글이 맞는지
        if (!feedId.equals(comment.getFeed().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        comment.setContent(dto.getContent());
        CommentDto.fromEntity(commentRepository.save(comment));
        ResponseDto response = new ResponseDto();
        response.setMessage("댓글이 수정되었습니다.");
        return response;
    }

    public void deleteComment(Long feedId, Long commentId) {

        if (!commentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);  // 자유롭게 상황대처

//        Optional<Feed> optionalFeed
//                = feedRepository.findById(feedId);
//        Feed feed = optionalFeed.get();

        Optional<Comment> optionalComment
                = commentRepository.findById(commentId);
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Comment comment = optionalComment.get();
        if (!feedId.equals(comment.getFeed().getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        commentRepository.deleteById(commentId);

    }



//    public ResponseDto addReply(
//            Long feedId,
//            Long commentId,
//            CommentDto commentDto) {
//        // 요청한 댓글이 존재하는지
//        Optional<Comment> optionalComment
//                = commentRepository.findById(commentId);
//        // 존재하지 않으면 예외 발생
//        if (optionalComment.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//        // 아니면 로직 진행
//        Comment comment = optionalComment.get();
//
//        // 대상 댓글이 대상 게시글의 댓글이 맞는지
//        if (!feedId.equals(comment.getFeed()))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//
//        comment.setReply(commentDto.getReply());
//        CommentDto.fromEntity(commentRepository.save(comment));
//        ResponseDto response = new ResponseDto();
//        response.setMessage("댓글에 답변이 추가되었습니다.");
//        return response;
//    }

    // deleteComment() 자유롭게 만들기

}










