package com.example.Final_Project_mutso.stomp.jpa;

import com.example.Final_Project_mutso.stomp.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Integer memberNum;

    @ManyToMany(mappedBy = "rooms")
    private List<UserEntity> users;

}