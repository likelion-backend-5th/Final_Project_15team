package com.example.Final_Project_mutso.stomp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ChattingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomName;

    @OneToMany(mappedBy = "chattingRoom")
    private List<UserChat> userChatList = new ArrayList<>();

    // User에 추가
//    @OneToMany(mappedBy = "user")
//    private List<UserChat> userChatList = new ArrayList<>();

}
