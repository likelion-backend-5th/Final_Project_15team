package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedLike;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.jwt.AuthenticationFacade;
import com.example.Final_Project_mutso.repository.FeedLikeRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class FeedLikeService {

    private final FeedRepository feedRepository;
    private final AuthenticationFacade authFacade;
    private final FeedLikeRepository feedLikeRepository;

    //좋아요 기능
    public String likeFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feed not found with id: " + feedId));

        UserEntity loginedUser = authFacade.getUser();

        if (loginedUser.getUsername().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<FeedLike> feedLike = feedLikeRepository.findByUserId(loginedUser.getId());
        if(feedLike.isEmpty()){
            FeedLike likes = FeedLike.builder()
                    .user(loginedUser)
                    .feed(feed)
                    .build();

            feed.addLikes(likes);
            feedRepository.save(feed);
//            return ResponseEntity.ok("좋아요가 반영되었습니다.");
        }
        else {
            feedLikeRepository.delete(feedLike.get());
//            return ResponseEntity.ok("좋아요가 취소되었습니다.");
        }

        return loginedUser.getUsername();

    }

    //좋아요 개수
    public int getCntFeedLikes(Long feedId) {
        return feedLikeRepository.countFeedLikeByFeed_Id(feedId);
    }

    public List<String> getFeedLikeUsers(Long feedId) {
        List<FeedLike> feedLike = feedLikeRepository.findByFeedId(feedId);
        List<String> likeUsers = new ArrayList<>();
        for (FeedLike e: feedLike) {
            likeUsers.add(e.getUser().getUsername());
        }
        return likeUsers;
    }

    public void deleteFeedLikes(Long feedId) {
        List<FeedLike> feedLikes = feedLikeRepository.findByFeedId(feedId);
        for (FeedLike e: feedLikes) {
            feedLikeRepository.delete(e);
        }
    }
}
