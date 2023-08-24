package com.example.Final_Project_mutso.entity;

import com.example.Final_Project_mutso.stomp.entity.ChattingRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String username;
    private String nickname;
    private String password;
    private String phonenumber;
    private String email;
    private String imageurl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_chattings")
    private List<ChattingRoom> rooms = new ArrayList<>();

    /*@OneToMany
    private List<Feed> feed;
    @OneToMany
    @JoinTable(name = "feed_likes")
    private List<Like> likes = new ArrayList<>();
    @OneToMany
    @JoinTable(name = "user_scraps")
    private List<Scrap> scraps = new ArrayList<>() ;
    @OneToMany
    @JoinTable(name = "user_follows")
    private List<Follow> follows = new ArrayList<>();
    @OneToMany
    @JoinTable(name = "user_chattings")
    private List<Chatting> chattings = new ArrayList<>() ;*/

}