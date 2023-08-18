package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.MusicPlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<MusicPlayList,Long> {
}
