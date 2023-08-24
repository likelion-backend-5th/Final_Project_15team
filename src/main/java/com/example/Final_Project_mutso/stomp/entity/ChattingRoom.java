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

    @ManyToMany(mappedBy = "rooms")
    private List<UserEntity> users;

    // user에 추가
//    @ManyToMany
//    @JoinTable(name = "user_chattings")
//    private List<ChattingRoom> rooms = new ArrayList<>();

}
