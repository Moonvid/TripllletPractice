package com.example.practice.controller;

import com.example.practice.domain.Posts;
import com.example.practice.domain.PostsRepository;
import com.example.practice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class WebRestController {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    // Pageable을 사용한 list
    /*
    @GetMapping("/post/list")
    public ModelAndView list(ModelAndView mav, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){

        Page<Posts> postsPage = postsRepository.findAll(pageable);
        System.out.println(postsPage);
        mav.addObject("postsPage", postsPage);

        mav.setViewName("post/list");
        //mav.addObject("posts", postsRepository.findAll());

        return mav;
    }
    */

//    // 일반 리스트
//    @GetMapping("/post/list")
//    public ModelAndView list(ModelAndView mav){
//
//        List<Posts> postsList = postsRepository.findAll();
//        mav.addObject("postsList", postsList);
//
//        mav.setViewName("post/list");
//        //mav.addObject("posts", postsRepository.findAll());
//
//        return mav;
//    }

    // 검색
    @GetMapping("/post/list")
    public ModelAndView search(@RequestParam(value="keyword", required = false) String keyword, ModelAndView mav){

        System.out.println("keyword = " + keyword);

        List<Posts> postsList = postsService.searchPosts(keyword);

        System.out.println(postsList);

        mav.addObject("postsList", postsList);
        mav.setViewName("/post/list");

        return mav;
    }



    // 글쓰기 폼 이동
    @GetMapping("/post/insertForm")
    public ModelAndView insertForm(ModelAndView mav){
        mav.setViewName("post/insert");

        return mav;
    }

    // 글쓰기
    @PostMapping("/post/insert")
    public ModelAndView insert(Posts p, ModelAndView mav){

        System.out.println(p.getCreatedDate());
        System.out.println(p.getModifiedDate());

        Posts posts = postsRepository.save(p);

        mav.addObject("posts",postsRepository.findAll());
        mav.setViewName("redirect:/post/list");

        return mav;
    }

    // 글 보기
    @GetMapping("/post/view/{id}")
    public ModelAndView view(@PathVariable("id") Long id, ModelAndView mav){

        Posts posts = postsRepository.getOne(id);

        mav.addObject("posts",posts);
        mav.setViewName("post/view");

        return mav;
    }


    // 삭제
    @GetMapping("/post/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, ModelAndView mav){

        Posts posts = postsRepository.getOne(id);   // id 값으로 해당 row 불러온다.
        posts.setDeletedDate(new Date());                // 불러온 row의 컬럼 중 deletedDate의 값을 현재 시간으로 변경
        postsRepository.save(posts);                // 바뀐 정보를 다시 저장

        mav.setViewName("redirect:/post/list");

        return mav;

    }

    // 수정 폼 이동
    @GetMapping("/post/updateForm/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id, ModelAndView mav){

        Posts posts = postsRepository.getOne(id);
        mav.addObject("posts", posts);
        mav.setViewName("post/update");

        return mav;

    }

    // 업데이트
    @PostMapping("/post/update/{id}")
    public ModelAndView update(@RequestParam("id") Long id, Posts p, ModelAndView mav){

        Posts posts = postsRepository.getOne(p.getId());

        posts.setTitle(p.getTitle());
        posts.setContent(p.getContent());
        posts.setModifiedDate(new Date());

        postsRepository.save(posts);

        mav.setViewName("redirect:/post/view/{id}");

        return mav;

    }


}
