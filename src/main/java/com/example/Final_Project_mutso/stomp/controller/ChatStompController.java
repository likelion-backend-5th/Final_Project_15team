package com.example.Final_Project_mutso.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
public class ChatStompController {

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/room";
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "roomdetails";
    }

//    @GetMapping
//    public String index() {
//        return "chat-lobby";
//    }
//
//    @GetMapping("{roomId}/{userId}")
//    public String enterRoom(){
//        return "chat-room";
//    }
}
