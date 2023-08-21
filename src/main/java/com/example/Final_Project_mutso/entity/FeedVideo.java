package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder

public class FeedVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private String image_url;

    public FeedVideo() {

    }
}
