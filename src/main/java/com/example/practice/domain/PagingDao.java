package com.example.practice.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PagingDao {

    List listCriteria(SearchCriteria cri);   // 전체 게시물 리스트를 불러오기 위한 메서드

    int countPaging(SearchCriteria cri);     // 전체 게시물을 count 하기 위한 메서드

}
