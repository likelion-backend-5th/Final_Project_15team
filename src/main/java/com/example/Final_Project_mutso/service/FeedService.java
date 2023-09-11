package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.dto.FeedListDto;
import com.example.Final_Project_mutso.entity.*;
import com.example.Final_Project_mutso.jwt.AuthenticationFacade;
import com.example.Final_Project_mutso.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class FeedService {

    private final FeedRepository feedRepository;
    private final FileService fileService;
    private final FeedImageRepository feedImageRepository;
    private final FeedVideoRepository feedVideoRepository;
    private final CommentService commentService;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedHashtagService feedHashtagService;
    private final AuthenticationFacade authFacade;
    private final UserRepository userRepository;

    public void createFeed(FeedDto dto, String tags, MultipartFile file) {

        UserEntity loginedUser = authFacade.getUser();

        Feed feed = new Feed();
        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feed.setDateTime(LocalDateTime.now());
        feed.setUser(loginedUser);
        feedRepository.save(feed);

        if (!tags.isEmpty()) {
            feedHashtagService.createFeedHashtag(feed, tags);
        }

        fileService.identFile(file, feed);

    }

    public List<FeedListDto> readFeedAll() {
        List<FeedListDto> feedList = new ArrayList<>();
        List<Feed> feeds = feedRepository.findAll();
        for (Feed feed: feeds) {
            FeedListDto dto = FeedListDto.fromEntity(feed);
            dto.setFileUrl(fileService.readFile(feed.getId()));
            feedList.add(dto);
        }
        return feedList;
    }


    public FeedDto readFeed(Long id) {
        Optional<Feed> optionalFeed
                = feedRepository.findById(id);
        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            FeedDto dto = FeedDto.fromEntity(feed);
            dto.setComments(commentService.readCommentAll(id));
            dto.setFileUrl(fileService.readFile(id));
            return dto;
        }
        return null;

    }

    public void updateFeed(Long id, FeedDto feedDto) {
        Optional<Feed> optionalFeed = feedRepository.findById(id);
//        Optional<FeedHashtag> optionalHashtag = feedRepository.findById(id);
        if (optionalFeed.isPresent()){
            Feed feedEntity = optionalFeed.get();
            feedEntity.setTitle(feedDto.getTitle());
            feedEntity.setContent(feedDto.getContent());

            feedRepository.save(feedEntity);
        }


    }

    public void deleteFeed(Long id) {
        Optional<Feed> optionalFeed = feedRepository.findById(id);
        if (optionalFeed.isPresent()) {
            feedRepository.deleteById(id);

        }
    }

    //좋아요 기능
    public void likeFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feed not found with id: " + feedId));

        UserEntity loginedUser = authFacade.getUser();

        if(feedLikeRepository.findByUserId(loginedUser.getId()) == null){
            FeedLike likes = FeedLike.builder()
                    .user(loginedUser)
                    .feed(feed)
                    .build();

            feed.addLikes(likes);
            feedRepository.save(feed);
        }
        else {
            FeedLike like = feedLikeRepository.findByUserId(loginedUser.getId());
            feedLikeRepository.delete(like);
        }

        FeedLike likes = FeedLike.builder()
                .feed(feed)
                .build();

        feed.addLikes(likes);
        feedRepository.save(feed);

    }

    //좋아요 개수
    public int getCntFeedLikes(Long feedId) {
        return feedLikeRepository.countFeedLikeByFeed_Id(feedId);
    }

}
