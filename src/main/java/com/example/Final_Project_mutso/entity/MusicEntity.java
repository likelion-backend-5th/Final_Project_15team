package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "music")
@Data
public class MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String musicId;
    private String musicName;
    private String artist;
    private String musicTime;
    private String imageUrlPath;


}
