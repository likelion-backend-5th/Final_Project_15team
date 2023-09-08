package com.example.Final_Project_mutso.service;

import com.example.Final_Project_mutso.entity.Hashtag;
import com.example.Final_Project_mutso.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class HashtagService {
    private final HashtagRepository hashtagRepository;

    public Optional<Hashtag> findByTagName(String tagName) {

        return hashtagRepository.findByHashtag(tagName);
    }

    public Hashtag save(String tagName) {

        Hashtag hashtag = new Hashtag();
        hashtag.setTagName(tagName);

        return hashtagRepository.save(hashtag);
    }
}
