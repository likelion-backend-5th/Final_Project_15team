package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user_follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToMany
    @JoinColumn(name="follower")
    private List<UserEntity> followerList = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name="following")
    private List<UserEntity> followingList = new ArrayList<>();

}
