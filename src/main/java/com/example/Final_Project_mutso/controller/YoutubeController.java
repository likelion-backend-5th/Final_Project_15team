package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.dto.YoutubeVideoDto;
import com.example.Final_Project_mutso.entity.MusicEntity;
import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.service.YoutubeVideoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
// cors 설정
@CrossOrigin(origins = "*")
public class YoutubeController {
    private final YoutubeVideoService service;

    private static final String YOUTUBE_APIKEY = "AIzaSyAj95x7jyV6YJCg1owyMqMoRTN-PHe15-E";

    @GetMapping("/search")
    public List<YoutubeVideoDto> searchByKeyword(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="10") Long items) throws JsonProcessingException {
        List<YoutubeVideoDto> searchList = service.search(search,items);

        return searchList;
    }

    @GetMapping("/search/{musicId}/{playlistname}/add")
    public MusicPlayList addPlayList(
            @PathVariable(value = "musicId") int musicId,
            @PathVariable(value = "playlistname") String playListName
    ){
        return service.addPlayList(playListName, musicId);
    }

    @GetMapping("/myplaylist")
    public List<MusicPlayList> getMyPlayList(){
        return service.getMyPlayList();
    }

    @GetMapping("/myplaylist/{playlistname}")
    public MusicPlayList getPlayList(
            @PathVariable(value = "playlistname") String playListName
    ){
        return service.getPlayList(playListName);
    }

}
