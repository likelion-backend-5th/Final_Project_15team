//package com.example.Final_Project_mutso.service;
//
//import com.example.Final_Project_mutso.entity.Feed;
//import com.example.Final_Project_mutso.entity.FeedHashtag;
//import com.example.Final_Project_mutso.entity.Hashtag;
//import com.example.Final_Project_mutso.repository.FeedHashtagRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//
//public class FeedHashtagService {
//
//    private final HashtagService hashtagService;
//
//    private final FeedHashtagRepository feedHashtagRepository;
//
//    public void saveHashtag(Feed feed, List<String> tagNames) {
//
//        if(tagNames.size() == 0) return;
//
//        tagNames.stream()
//                .map(hashtag ->
//                        hashtagService.findByTagName(hashtag)
//                                .orElseGet(() -> hashtagService.save(hashtag)))
//                .forEach(hashtag -> mapHashtagToFeed(feed, hashtag));
//    }
//
//    private Long mapHashtagToFeed(Feed feed, Hashtag hashtag) {
//
//        return feedHashtagRepository.save(new FeedHashtag(feed, hashtag)).getId();
//    }
//
//    public List<FeedHashtag> findHashtagListByFeed(Feed feed) {
//
//        return feedHashtagRepository.findAllByFeed(feed);
//    }
//}
