package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.PlayListFeedDto;
import com.example.Final_Project_mutso.dto.PlayListRequestDto;
import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.entity.PlayListShareFeed;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.PlayListFeedRepository;
import com.example.Final_Project_mutso.repository.PlayListRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayListFeedService {
    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;
    private final PlayListFeedRepository playListFeedRepository;
    public void writeFeed(String title, String Description, int idx) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity user = optionalUser.get();

        Optional<List<MusicPlayList>> optionalPlaylist = playListRepository.findByUser(user);
        if(optionalPlaylist.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<MusicPlayList> musicPlayLists = optionalPlaylist.get();

        PlayListShareFeed feed = new PlayListShareFeed();

        feed.setTitle(title);
        feed.setUser(user);
        feed.setDescription(Description);
        log.info(String.valueOf(idx));
        feed.setPlayList(musicPlayLists.get(idx-1));

        playListFeedRepository.save(feed);

    }

    public Page<PlayListRequestDto> readItemPaged(Integer page, Integer limit) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity user = optionalUser.get();

        Pageable pageable = PageRequest.of(
                page, limit, Sort.by("id").ascending());

        Page<PlayListShareFeed> EntityPage
                =playListFeedRepository.findAllByUser(user,pageable);

        Page<PlayListRequestDto> itemDtoPage
                = EntityPage.map(PlayListRequestDto::fromEntity);
        return itemDtoPage;
    }
}
