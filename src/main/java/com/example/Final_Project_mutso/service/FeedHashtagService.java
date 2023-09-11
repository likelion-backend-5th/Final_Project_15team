package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedHashtag;
import com.example.Final_Project_mutso.entity.Hashtag;
import com.example.Final_Project_mutso.repository.FeedHashtagRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class FeedHashtagService {

    private final HashtagService hashtagService;
    private final FeedRepository feedRepository;
    private final FeedHashtagRepository feedHashtagRepository;

    public void createFeedHashtag(Feed feed, String tags) {
        String[] str = tags.split(" ");
        System.out.println(str.length);

        for(int i=0; i<str.length; i++) {
            Optional<Hashtag> opHash = hashtagService.availHashtag(str[i]);
            Hashtag hashtag;
            if(opHash.isEmpty()){
                hashtagService.createHashtag(str[i]);
                hashtag = hashtagService.availHashtag(str[i]).get();
            } else {
                hashtag = opHash.get();
            }

            FeedHashtag feedHashtag = new FeedHashtag();
            feedHashtag.setHashtag(hashtag);
            feedHashtag.setFeed(feed);
            feedHashtagRepository.save(feedHashtag);

        }

    }

//    public String updateFeedHashtag(Feed feed, String tags) {
//        if (!tags.isEmpty()) {
//            feedHashtagService.createFeedHashtag(feed, tags);
//        }
//
//    }

    public void deleteFeedHashtag(Long feedId) {
        Optional<Feed> optionalFeed = feedRepository.findById(feedId);
        List<FeedHashtag> list = feedHashtagRepository.findAllByFeed_Id(feedId);
        for (FeedHashtag feedHashtag : list) {
            feedHashtagRepository.deleteById(feedHashtag.getId());
        }
    }
}
