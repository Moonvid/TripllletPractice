package com.example.practice.controller;

import com.example.practice.domain.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts", postsRepository.findAll());
        return "main";
    }


}
