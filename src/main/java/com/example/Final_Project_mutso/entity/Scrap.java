package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne UserEntity user;

//    @ManyToOne Feed feed;
//
//    @ManyToMany
//    @JoinColumn(name="scrap")
//    private List<Feed> scrapList = new ArrayList<>();

}