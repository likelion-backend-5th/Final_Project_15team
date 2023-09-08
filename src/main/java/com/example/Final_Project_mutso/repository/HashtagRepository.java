package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    Optional<Hashtag> findByHashtag(String hashtag);

}
