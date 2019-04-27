package com.example.practice.controller;

import com.example.practice.domain.Posts;
import com.example.practice.domain.PostsRepository;
import com.example.practice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static java.time.LocalDateTime.now;

@RestController
@AllArgsConstructor
public class WebRestController {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    /*
    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){

        //postsRepository.save(dto.toEntity());
        return postsService.save(dto);

    }
    */

    // 글쓰기
    @PostMapping("/insert")
    public ModelAndView insert(Posts p){

        Posts posts = postsRepository.save(p);

        ModelAndView mav = new ModelAndView();
        mav.addObject("posts",postsRepository.findAll());
        mav.setViewName("main");

        return mav;
    }

    // 글쓰기 폼 이동
    @GetMapping("/insertForm")
    public ModelAndView insertForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("insert");

        return mav;
    }

    // 글 보기
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView();

        Posts posts = postsRepository.getOne(id);

        mav.addObject("posts",posts);
        mav.setViewName("view");

        return mav;
    }


    // 삭제
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){

        Posts posts = postsRepository.getOne(id);   // id 값으로 해당 row 불러온다.
        posts.setDeletedDate(new Date());                // 불러온 row의 컬럼 중 deletedDate의 값을 현재 시간으로 변경
        postsRepository.save(posts);                // 바뀐 정보를 다시 저장

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/");

        return mav;

    }

    // 수정 폼 이동
    @GetMapping("/updateForm/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id){

        ModelAndView mav = new ModelAndView();
        Posts posts = postsRepository.getOne(id);
        mav.addObject("posts", posts);
        mav.setViewName("update");

        return mav;

    }

    // 업데이트
    @PostMapping("/update/{id}")
    public ModelAndView update(@RequestParam("id") Long id, Posts p){

        ModelAndView mav = new ModelAndView();

        Posts posts = postsRepository.getOne(p.getId());

        posts.setTitle(p.getTitle());
        posts.setContent(p.getContent());
        posts.setModifiedDate(new Date());

        postsRepository.save(posts);

        mav.setViewName("redirect:/view/{id}");

        return mav;

    }

    // 검색
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("title") String title, ModelAndView mav){

        mav.addObject("list", postsService.searchPosts(title));
        mav.setViewName("search");

        System.out.println(postsService.searchPosts(title));

        return mav;
    }


}
