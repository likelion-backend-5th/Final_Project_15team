package com.example.Final_Project_mutso.stomp.controller;

import com.example.Final_Project_mutso.stomp.dto.ChatMessageDto;
import com.example.Final_Project_mutso.stomp.dto.ChatRoomDto;
import com.example.Final_Project_mutso.stomp.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatRestController {
    private final ChatService chatService;


    // 채팅방 생성하기
    @PostMapping("rooms")
    public ResponseEntity<ChatRoomDto> createRoom(@RequestBody ChatRoomDto chatRoomDto){
        return ResponseEntity.ok(chatService.createChatRoom(chatRoomDto));
    }

    // 채팅방 조회하기
    @GetMapping("rooms")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms(){
        return ResponseEntity.ok(chatService.getChatRooms());
    }

    //
    @GetMapping("rooms/{id}/name")
    public ResponseEntity<ChatRoomDto> getRoomName(@PathVariable("id") Long roomId) {
        return ResponseEntity.ok(chatService.findRoomById(roomId));
    }

    // 메세지 조회
    @GetMapping("rooms/{id}/message")
    public List<ChatMessageDto> readMessage(
            @PathVariable("id") Long id
    ){
        return chatService.readMessage(id);
    }
}