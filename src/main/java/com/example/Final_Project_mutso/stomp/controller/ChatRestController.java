package com.example.Final_Project_mutso.stomp.controller;

import com.example.Final_Project_mutso.stomp.dto.ChatMessage;
import com.example.Final_Project_mutso.stomp.service.ChatService;
import com.example.Final_Project_mutso.stomp.dto.ChatRoom;
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
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoom chatRoom){
        return ResponseEntity.ok(chatService.createChatRoom(chatRoom));
    }

    // 채팅방 조회하기
    @GetMapping("rooms")
    public ResponseEntity<List<ChatRoom>> getChatRooms(){
        return ResponseEntity.ok(chatService.getChatRooms());
    }

    //
    @GetMapping("rooms/{id}/name")
    public ResponseEntity<ChatRoom> getRoomName(@PathVariable("id") Long roomId) {
        return ResponseEntity.ok(chatService.findRoomById(roomId));
    }

//    // 메세지 보내기
//    @PostMapping("rooms/chat")
//    public ResponseEntity<ChatMessage> sendMessage(
//            @PathVariable("id") Long roomId,
//            @RequestBody ChatMessage dto
//    ){
//        return ResponseEntity.ok(chatService.sendMessage(dto, roomId));
//    }
}