package com.example.Final_Project_mutso.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
public class ChatStompController {

    @GetMapping
    public String index() {
        return "chat-lobby";
    }

    @GetMapping("{roomId}/{userId}")
    public String enterRoom(){
        return "chat-room";
    }
}