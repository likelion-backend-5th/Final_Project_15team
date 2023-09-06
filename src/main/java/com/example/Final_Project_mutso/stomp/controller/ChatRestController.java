package com.example.Final_Project_mutso.stomp.controller;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.service.ChatService;
//import com.example.Final_Project_mutso.stomp.service.MessageService;
//import com.example.Final_Project_mutso.stomp.service.MessageService;
import io.opencensus.resource.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatRestController {
    private final ChatService chatService;
//    private final MessageService messageService;

    // 채팅방 생성하기
    @PostMapping(value = "rooms", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ChatRoomDto> createRoom(
            @RequestPart ChatRoomDto chatRoomDto,
            @RequestPart("image") MultipartFile file
    ) throws IOException {

        return ResponseEntity.ok(chatService.createChatRoom(chatRoomDto , file));
    }

    // 채팅방 목록 조회하기
    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(){
        return ResponseEntity.ok(chatService.getChatRooms());
    }


    // 채팅방 삭제
    @DeleteMapping("room/{id}/delete")
    public void deleteChatRoom(@PathVariable("id") Long id){
        chatService.deleteChatRoom(id);
    }


    // 파일 업로드
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile image) throws IOException {
        return chatService.uploadFile(image);
    }

    // 닉네임 받아오기
    @GetMapping("username")
    public String getNickname(){
        return chatService.getNickname();
    }
}