package com.example.Final_Project_mutso.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="feedId")
    @ManyToOne
    private PlayListShareFeed feed;


    @JoinColumn(name="userId")
    @ManyToOne
    private UserEntity user;


}
