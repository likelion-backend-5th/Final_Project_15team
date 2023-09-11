package com.example.Final_Project_mutso.stomp.entity;

import com.example.Final_Project_mutso.entity.UserEntity;
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
    private String imageUrl;
    private int userCount;

    // 각 채팅방은 여러 유저의 참여를 허용하지만, 각 유저는 하나의 채팅방에만 참여할 수 있다.
    // 저희가 지금 따로 유저가 속한 채팅방을 관리하고 있는 것이 아니라 한 곳에서 채팅하고 나가고 반복이기 때문에 변경하였습니다.
    @ManyToOne
    private UserEntity user;

//    @ManyToMany(mappedBy = "rooms")
//    private List<UserEntity> users;
}
