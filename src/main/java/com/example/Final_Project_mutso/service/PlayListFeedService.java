//package com.example.Final_Project_mutso.service;
//
//import com.example.Final_Project_mutso.dto.PlayListRequestDto;
//import com.example.Final_Project_mutso.entity.Likes;
//import com.example.Final_Project_mutso.entity.MusicPlayList;
//import com.example.Final_Project_mutso.entity.PlayListShareFeed;
//import com.example.Final_Project_mutso.entity.UserEntity;
//import com.example.Final_Project_mutso.repository.LikeRepository;
//import com.example.Final_Project_mutso.repository.PlayListFeedRepository;
//import com.example.Final_Project_mutso.repository.PlayListRepository;
//import com.example.Final_Project_mutso.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PlayListFeedService {
//    private final PlayListRepository playListRepository;
//    private final UserRepository userRepository;
//    private final PlayListFeedRepository playListFeedRepository;
//    private final LikeRepository likeRepository;
//    public void writeFeed(String title, String Description, int idx) {
//        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
//        if(optionalUser.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        UserEntity user = optionalUser.get();
//
//        Optional<List<MusicPlayList>> optionalPlaylist = playListRepository.findByUser(user);
//        if(optionalPlaylist.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        List<MusicPlayList> musicPlayLists = optionalPlaylist.get();
//
//        PlayListShareFeed feed = new PlayListShareFeed();
//
//        feed.setTitle(title);
//        feed.setUser(user);
//        feed.setDescription(Description);
//        log.info(String.valueOf(idx));
//        feed.setPlayList(musicPlayLists.get(idx-1));
//
//        playListFeedRepository.save(feed);
//
//    }
//
//    public Page<PlayListRequestDto> readItemPaged(Integer page, Integer limit) {
//        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
//        if(optionalUser.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        UserEntity user = optionalUser.get();
//
//        Pageable pageable = PageRequest.of(
//                page, limit, Sort.by("id").ascending());
//
//        Page<PlayListShareFeed> EntityPage
//                =playListFeedRepository.findAllByUser(user,pageable);
//
//        Page<PlayListRequestDto> itemDtoPage
//                = EntityPage.map(PlayListRequestDto::fromEntity);
//        return itemDtoPage;
//    }
//
//    public PlayListRequestDto readFeed(Long feedId) {
//        Optional<PlayListShareFeed> feedOptional = playListFeedRepository.findById(feedId);
//        if(feedOptional.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        PlayListShareFeed playListShareFeed = feedOptional.get();
//
//        return PlayListRequestDto.fromEntity(playListShareFeed);
//    }
//
//    public ResponseEntity<Map<String,String>> likes(Long id) {
//        Map<String, String> responseBody = new HashMap<>();
//
//        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
//        if(optionalUser.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        UserEntity userEntity = optionalUser.get();
//
//        Optional<PlayListShareFeed> optionalPlayListShareFeed = playListFeedRepository.findById(id);
//        if(optionalPlayListShareFeed.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        PlayListShareFeed feed = optionalPlayListShareFeed.get();
//
//        if(feed.getUser().getUsername().equals(userEntity.getUsername())){
//            responseBody.put("좋아요 오류","본인의 글에는 좋아요를 누를 수 없습니다.");
//            return ResponseEntity.ok(responseBody);
//        }
//        Optional<Likes> optionalLikes = likeRepository.findByFeedAndUser(feed,userEntity);
//        if(optionalLikes.isEmpty()){ //isEmpty() -> 좋아요가 눌러있지 않은 상태라면
//            Likes likes = new Likes();
//            likes.setFeed(feed);
//            likes.setUser(userEntity);
//
//            likeRepository.save(likes);
//            responseBody.put("좋아요","등록되었습니다");
//            return ResponseEntity.ok(responseBody);
//        }else { // 반대라면 좋아요를 취소
//            likeRepository.delete(optionalLikes.get());
//            responseBody.put("좋아요","취소되었습니다.");
//            return ResponseEntity.ok(responseBody);
//        }
//
//    }
//
//    public Long getLikes(Long id) {
//        Optional<PlayListShareFeed> optionalArticle = playListFeedRepository.findById(id);
//        if(optionalArticle.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        PlayListShareFeed feed = optionalArticle.get();
//
//        return likeRepository.countAllByFeed(feed); //LikesRepository 의 countAllByArticle(Article article) 메소드 이용
//
//    }
//}