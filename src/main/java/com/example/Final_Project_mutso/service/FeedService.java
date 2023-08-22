package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.repository.FeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
//@RequiredArgsConstructor
@Service

public class FeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository){
        this.feedRepository = feedRepository;
        FeedDto dto = new FeedDto();
        dto.setTitle("title");
        dto.setContent("content");

        createFeed(dto);
    }

    public void createFeed(FeedDto dto) {
        Feed feed = new Feed();
//        feed.setUser(userEntity);
        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feedRepository.save(feed);

    }

    public List<Feed> readFeedAll() {
        return feedRepository.findAll();
    }


    public Feed readFeed(Long id) {
        Optional<Feed> optionalFeed
                = feedRepository.findById(id);
        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            return feed;
        }
        return null;
    }

    public void updateFeed(Long id, FeedDto feedDto) {
        Optional<Feed> optionalFeed = feedRepository.findById(id);
        if (optionalFeed.isPresent()){
            Feed feedEntity = optionalFeed.get();
            feedEntity.setTitle(feedDto.getTitle());
            feedEntity.setContent(feedDto.getContent());
            // 이미지 추가

            feedRepository.save(feedEntity);

        }
    }

    public void deleteFeed(Long id) {
        Optional<Feed> optionalFeed = feedRepository.findById(id);
        if (optionalFeed.isPresent()) {
            feedRepository.deleteById(id);

        }
    }

}
