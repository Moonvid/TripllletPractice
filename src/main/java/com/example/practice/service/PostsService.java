package com.example.practice.service;

import com.example.practice.domain.Posts;
import com.example.practice.domain.SearchDao;
import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class PostsService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private SearchDao searchDao;

    // 메인 페이지 검색
    public List searchPosts(Map<String, Object> params){

        searchDao = sqlSessionTemplate.getMapper(SearchDao.class);

        List<Posts> results = searchDao.searchPosts(params);

        return results;

    }

    // 페이징 할 게시물 카운트
    public int count(Map<String, Object> params){

        System.out.println("params = " + params);

        searchDao = sqlSessionTemplate.getMapper(SearchDao.class);

        int resultCnt = searchDao.count(params);

        return resultCnt;

    }







    /*// 글 저장하는 service
    @Transactional
    public Long save(PostsSaveRequestDto dto){

        return postsRepository.save(dto.toEntity()).getId();
    }


    // List 불러오는 service
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc(){
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)//.map(posts -> new PostsMainResponseDto(posts)) 로 표현 가능, 현재는 람다식 사용
                .collect(Collectors.toList());
    }


    // List 글 읽기
    @Transactional
    public Posts findOne(Long id){

        return postsRepository.getOne(id);

    }


    // 글 삭제

    public void delete(Long id) {
        postsRepository.deleteById(id);
    }


    // 글 삭제(데이터 삭제가 아닌 컬럼 속성 변경으로 숨기기)
    @Transactional
    public void delete(@RequestParam("id") long id){

        Posts posts = postsRepository.getOne(id);
        posts.setDeletedDate(now());
        postsRepository.save(posts);

    }

    // 업데이트

    public void update(Long id,Posts p) {

        Posts posts = postsRepository.getOne(id);
        posts.setTitle(p.getTitle());
        posts.setContent(p.getContent());
        posts.setWriter(p.getWriter());
        postsRepository.save(posts);

    }*/


}
