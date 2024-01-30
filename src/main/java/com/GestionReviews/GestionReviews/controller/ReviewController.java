package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String ViewUsersPage(Model model){
        model.addAttribute("listReviews", reviewService.getAll());
        return "reviews";
    }

}
