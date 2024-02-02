package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/")
    public String ViewUsersPage(Model model){
        model.addAttribute("listReviews", reviewService.getAll());
        model.addAttribute("reviewDto", new ReviewDto()); // Add this line
        return "index";
    }
    @PostMapping("/submit-review")
    public String submitReview(@ModelAttribute ReviewDto reviewDto) {
        reviewDto.setUserId(1L);
        reviewDto.setReaction("LIKE");
        reviewDto.setDate(LocalDate.now());

        reviewService.save(reviewDto);
        return "redirect:/";
    }

}
