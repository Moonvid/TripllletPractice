package com.example.practice.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchDao {

    List<Posts> searchPosts(String keyword);


}
