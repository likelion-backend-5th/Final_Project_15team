package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FeedListDto;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedHashtag;
import com.example.Final_Project_mutso.entity.Hashtag;
import com.example.Final_Project_mutso.repository.FeedHashtagRepository;
import com.example.Final_Project_mutso.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class HashtagService {
    private final HashtagRepository hashtagRepository;
    private final FeedHashtagRepository feedHashtagRepository;
    private final FileService fileService;

    public Hashtag createHashtag(String newTag) {
        Hashtag hashtag = new Hashtag();
        hashtag.setTagName(newTag);
        return hashtagRepository.save(hashtag);
    }

    public Optional<Hashtag> availHashtag(String tagName) {
        return hashtagRepository.findByTagName(tagName);
    }

    public List<FeedListDto> searchHashtag(String keyword) {

        List<FeedListDto> feedList = new ArrayList<>();
        Optional<Hashtag> hashtag = hashtagRepository.findByTagName(keyword);
        List<FeedHashtag> feedHashtagList = feedHashtagRepository.findAllByHashtag(hashtag.get());

        for (FeedHashtag feedHashtag: feedHashtagList) {
            Feed feed = feedHashtag.getFeed();
            FeedListDto dto = FeedListDto.fromEntity(feed);
            dto.setFileUrl(fileService.readFile(feed.getId()));
            feedList.add(dto);
        }

        return feedList;
    }

}
