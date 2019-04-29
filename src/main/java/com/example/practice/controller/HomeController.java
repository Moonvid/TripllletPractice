package com.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){

        return "main";
    }


    /*
    @GetMapping(value="/")
    public ModelAndView getList(@ModelAttribute("cri")SearchCriteria cri, ModelAndView mav){


        System.out.println(postsRepository.findAll());
        // mav.addObject("posts",pagingService.listCriteria(cri));

        PageMaker pageMaker = new PageMaker();
        pageMaker.setSearchCri(cri);

        //pageMaker.setTotalCount(pagingService.listCountCriteria(cri));

        mav.addObject("pageMaker", pageMaker);
        mav.setViewName("main");

        return mav;

    }
    */


    // 확인



}


