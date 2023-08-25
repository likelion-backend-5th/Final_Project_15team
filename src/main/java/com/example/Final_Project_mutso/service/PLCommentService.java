package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.dto.PLCommentDto;
import com.example.Final_Project_mutso.entity.PlayListCommentEntity;
import com.example.Final_Project_mutso.entity.PlayListShareFeed;
import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.PlayListCommentRepository;
import com.example.Final_Project_mutso.repository.PlayListFeedRepository;
import com.example.Final_Project_mutso.repository.PlayListRepository;
import com.example.Final_Project_mutso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PLCommentService {
    private final UserRepository userRepository;
    private final PlayListCommentRepository playListCommentRepository;
    private final PlayListFeedRepository playListFeedRepository;

    public void post(Long id, String content){
        Optional<UserEntity> optionalUser = userRepository.findByUsername("test");
        if(optionalUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserEntity user = optionalUser.get();

        Optional<PlayListShareFeed> optionalPlayListShareFeed = playListFeedRepository.findById(id);
        if(optionalPlayListShareFeed.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PlayListShareFeed playListShareFeed = optionalPlayListShareFeed.get();

        PlayListCommentEntity playListCommentEntity =new PlayListCommentEntity();
        playListCommentEntity.setUser(user);
        playListCommentEntity.setFeed(playListShareFeed);
        playListCommentEntity.setContent(content);

        if(playListShareFeed.getComments().isEmpty()){
            List<PlayListCommentEntity> commentEntityList = new ArrayList<>();
            commentEntityList.add(playListCommentEntity);
            playListShareFeed.setComments(commentEntityList);
        }else{
            List<PlayListCommentEntity> commentEntityList = playListShareFeed.getComments();
            commentEntityList.add(playListCommentEntity);
            playListShareFeed.setComments(commentEntityList);
        }

        playListCommentRepository.save(playListCommentEntity);
        playListFeedRepository.save(playListShareFeed);



    }
}
