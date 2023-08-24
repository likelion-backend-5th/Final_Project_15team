package com.example.Final_Project_mutso.controller;

import com.example.Final_Project_mutso.entity.MusicEntity;
import com.example.Final_Project_mutso.service.YoutubeVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
@RequiredArgsConstructor
// cors 설정
@CrossOrigin(origins = "*")
public class MusicController {
    private final YoutubeVideoService service;

    @GetMapping("/youtube/search/{musicId}/playmusic")
    public String playMusic(
            @PathVariable(value = "musicId") int musicId,
            Model model
    ){
        MusicEntity musicEntity = service.returnVideo(musicId);
        String url = musicEntity.getMusicId().split("=")[1];

        log.info(url);

        model.addAttribute("videoEmbedUrl",url);

        return "player"; // Thymeleaf 템플릿 이름 반환
    }
}
