package com.example.FilterReviews.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FilterReviewsController {
    @GetMapping
    public String getFilterPage(){
        return "filterPage";
    }
}
