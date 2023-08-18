package com.example.Final_Project_mutso;

import com.example.Final_Project_mutso.dto.YoutubeVideoDto;
import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.service.YoutubeVideoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeVideoService service;
    @GetMapping("/search")
    public String searchByKeyword(
            @RequestParam(value="word", required=true) String search,
            @RequestParam(value="items", required=false, defaultValue="5") String items) throws JsonProcessingException {
        int max = Integer.parseInt(items);
        List<YoutubeVideoDto> searchList = service.search(search,max);

        // JSON으로 변환
        String result = new ObjectMapper().writeValueAsString(searchList);
        System.out.println(result);
        return result;
    }

    @GetMapping("/search/{musicId}/addplaylist")
    public MusicPlayList addPlayList(
            @PathVariable(value = "musicId") int musicId
    ){
        return service.addPlayList(musicId);
    }

}
