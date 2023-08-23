package com.example.Final_Project_mutso.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 중간 테이블 생성됨.
    // fromUser가 toUser를 following 함.
    // toUser를 fromUser가 follower 함.

    @ManyToOne
    @JoinColumn(name="fromUserId")
    //@JsonIgnoreProperties({"images"})
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="toUserId")
    //@JsonIgnoreProperties({"images"})
    private User toUser;

}
