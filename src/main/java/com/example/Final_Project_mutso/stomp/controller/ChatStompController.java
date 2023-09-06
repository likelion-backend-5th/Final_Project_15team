package com.example.Final_Project_mutso.stomp.controller;

import com.example.Final_Project_mutso.entity.UserEntity;
import com.example.Final_Project_mutso.repository.UserRepository;
import com.example.Final_Project_mutso.stomp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatStompController {
    private final ChatService chatService;
    private final UserRepository userRepository;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/room";
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(
            Model model,
            @PathVariable String roomId) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        UserEntity user = userRepository.findAllByUsername(username);
//        String nickname = user.getNickname();
//
//        model.addAttribute("roomId", roomId);
//        model.addAttribute("nickname", nickname);

//        chatService.checkNickname(nickname);
        return "/stompTest";
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
