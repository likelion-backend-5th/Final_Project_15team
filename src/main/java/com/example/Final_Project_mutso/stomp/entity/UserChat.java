package com.example.Final_Project_mutso.stomp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChattingRoom chattingRoom;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
