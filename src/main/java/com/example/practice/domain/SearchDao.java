package com.example.practice.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearchDao {

    List<Posts> searchPosts(String keyword);

    //int count(Map<String, Object> params);

}
