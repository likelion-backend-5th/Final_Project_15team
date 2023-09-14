package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FeedDto;
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

import java.io.IOException;
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
    private final FeedFileRepository feedFileRepository;
    private final CommentService commentService;
    private final FeedHashtagService feedHashtagService;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;

    public void createFeed(FeedDto dto, String tags, MultipartFile file) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity loginedUser = userRepository.findAllByUsername(username);

//        UserEntity loginedUser = authFacade.getUser();
        if (username.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Feed feed = new Feed();
        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feed.setDateTime(LocalDateTime.now());
        feed.setUser(loginedUser);
        feedRepository.save(feed);

        if (!tags.isEmpty()) {
            feedHashtagService.createFeedHashtag(feed, tags);
        }

        if (!file.isEmpty()) { // 첨부 파일이 존재한다면

            String url = fileService.createFile(file);
            FeedFile feedFile = new FeedFile();
            feedFile.setFeed(feed);
            feedFile.setImageUrl(url);
            feedFileRepository.save(feedFile);
        }

    }

    public List<FeedDto> readFeedAll() {
        List<FeedDto> feedList = new ArrayList<>();
        List<Feed> feeds = feedRepository.findAll();
        for (Feed feed: feeds) {
            FeedDto dto = FeedDto.fromEntity(feed);
            dto.setComments(commentService.readCommentAll(feed.getId()));
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

    public void deleteFeedScrap(Long feedId) {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        List<Scrap> scraps = scrapRepository.findByFeed(optionalFeed.get());
        for (Scrap e : scraps) {
            scrapRepository.delete(e);
        }
    }

}
