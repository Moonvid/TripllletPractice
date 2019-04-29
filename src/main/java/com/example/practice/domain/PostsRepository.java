package com.example.practice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {


    @Query("SELECT  p FROM Posts p WHERE p.deletedDate IS NULL ORDER BY p.id DESC")
    List<Posts> findAll();

}
