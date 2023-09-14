package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
//    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
//    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToMany
    @JoinColumn(name="scrap")
    private List<Feed> scrapList = new ArrayList<>();

}