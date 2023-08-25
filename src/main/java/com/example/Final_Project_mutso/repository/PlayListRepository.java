package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.MusicPlayList;
import com.example.Final_Project_mutso.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayListRepository extends JpaRepository<MusicPlayList,Long> {
    Optional<MusicPlayList> findByName(String playListName);

    Optional<List<MusicPlayList>> findByUser(UserEntity user);
}   
