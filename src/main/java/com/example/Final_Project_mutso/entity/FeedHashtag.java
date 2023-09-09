package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FeedHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;


    public FeedHashtag() {

    }
}
