package com.example.Final_Project_mutso.entity;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    private String nickname;
    private String password;
    private String phonenumber;
    private String email;
    private String profileImage;
    private String introduction;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_chattings")
    private List<ChattingRoom> rooms = new ArrayList<>();

    @OneToMany
    private List<Feed> feeds;

}
