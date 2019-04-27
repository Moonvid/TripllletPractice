package com.example.practice.controller;

import com.example.practice.domain.Posts;
import com.example.practice.domain.PostsRepository;
import com.example.practice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class WebController {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private PostsService postsService;

    /*
    @GetMapping("/") // == RequestMapping(value="/", method=RequestMethod.GET)
    public String main(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        return "main";
    }
    */

    /*
    @GetMapping("/view")
    public ModelAndView readPosts(@RequestParam("id") Long id){

        ModelAndView modelAndView = new ModelAndView();

        Posts posts = postsService.findOne(id);

        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("view");

        return modelAndView;
    }

    @GetMapping("/editForm")
    public ModelAndView moveUpdateForm(@RequestParam("id") Long id){

        ModelAndView modelAndView = new ModelAndView();

        Posts posts = postsService.findOne(id);

        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("update");

        return modelAndView;



    }

    // 글 수정
    @GetMapping("/edit")
    public String updatePosts(@RequestParam("id") Long id, Posts p){

        System.out.println("값 전달 확인");
        System.out.println(id);
        System.out.println(p.getTitle());
        System.out.println(p.getWriter());
        System.out.println(p.getContent());
        postsService.update(id, p);

        return "redirect:/view?id="+id;
    }

    /*
    @GetMapping("/delete")
    public String deletePosts(@RequestParam("id") Long id){

        postsService.delete(id);
        return "redirect:/";
    }
    */

    // deleteDate 컬럼 값만 update 시켜서
    // 목록 리스트를 불러올때 deleteDate 컬럼이 null인것만 불러오도록 하면 된다.

    // 이 방법은 data 완전 delete 방식
    /*
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id){

        postsService.delete(id);

        return "redirect:/";

    }
    */











}
