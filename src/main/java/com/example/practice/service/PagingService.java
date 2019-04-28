package com.example.practice.service;

import com.example.practice.domain.PagingDao;
import com.example.practice.domain.SearchCriteria;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PagingService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private PagingDao pagingDao;

    // 조건 없이 전체 데이터를 가져오는 메서드
    public List listCriteria(SearchCriteria cri){

        pagingDao = sqlSessionTemplate.getMapper(PagingDao.class);

        List list = pagingDao.listCriteria(cri);

        return list;

    }

    // 조건 없이 전체 데이터의 개수를 카운트 하는 메서드
    public int listCountCriteria(SearchCriteria cri){

        pagingDao = sqlSessionTemplate.getMapper(PagingDao.class);

        int resultCnt = pagingDao.countPaging(cri);

        return resultCnt;

    }

}
