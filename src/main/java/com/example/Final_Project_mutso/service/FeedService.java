package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.FeedDto;
import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.FeedImage;
import com.example.Final_Project_mutso.entity.FeedVideo;
import com.example.Final_Project_mutso.repository.FeedImageRepository;
import com.example.Final_Project_mutso.repository.FeedRepository;
import com.example.Final_Project_mutso.repository.FeedVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void createFeed(FeedDto dto, MultipartFile file) {
        Feed feed = new Feed();
//        feed.setUser(userEntity);
        feed.setTitle(dto.getTitle());
        feed.setContent(dto.getContent());
        feed.setHashtag(dto.getHashtag());
        feedRepository.save(feed);


        if (!file.isEmpty()) { // 첨부 파일이 존재한다면
            String fileName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
            String fileExtension = fileName.substring(fileName.lastIndexOf("."),fileName.length());

            if (fileExtension.equals(".jpg")||fileExtension.equals(".png")){// 확장자가 이미지일 때
                String url = fileService.createFile(file);
                FeedImage feedImage = new FeedImage();
                feedImage.setFeed(feed);
                feedImage.setImageUrl(url);
                feedImageRepository.save(feedImage);
            } else if (fileExtension.equals(".mp4")||fileExtension.equals(".avi")){ //영상 확장자일 때
                String url = fileService.createFile(file);
                FeedVideo feedVideo = new FeedVideo();
                feedVideo.setFeed(feed);
                feedVideo.setVideoUrl(url);
                feedVideoRepository.save(feedVideo);
            }
            else{
                System.out.println(".jpg, .png, .mp4, .avi 확장자 파일을 선택해주세요");
            }

        }

    }

    public List<FeedDto> readFeedAll() {
        List<FeedDto> feedList = new ArrayList<>();
        List<Feed> feeds = feedRepository.findAll();
        for (Feed feed: feeds) {
            feedList.add(FeedDto.fromEntity(feed));
        }
        return feedList;
    }


    public FeedDto readFeed(Long id) {
        Optional<Feed> optionalFeed
                = feedRepository.findById(id);
        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            return FeedDto.fromEntity(feed);
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
            feedEntity.setHashtag(feedDto.getHashtag());

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
