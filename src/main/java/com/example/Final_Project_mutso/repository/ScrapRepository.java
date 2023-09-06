package com.example.Final_Project_mutso.repository;

import com.example.Final_Project_mutso.entity.Feed;
import com.example.Final_Project_mutso.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findByFeed(Feed feed);

}
