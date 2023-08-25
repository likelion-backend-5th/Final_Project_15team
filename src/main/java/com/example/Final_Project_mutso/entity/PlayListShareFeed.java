package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "PlayList_Feed")
@Data
public class PlayListShareFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String title;
    private String description;

    @ManyToOne
    private MusicPlayList playList;

    @OneToMany(mappedBy = "feed")
    private List<PlayListCommentEntity> comments;

}
