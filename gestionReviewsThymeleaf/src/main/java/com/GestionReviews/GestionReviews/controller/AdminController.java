package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import org.springframework.ui.Model;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final ReviewService reviewService;

    public AdminController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/index")
    public String ViewReviews(Model model){
        model.addAttribute("reviews", reviewService.getAll());
        return "admin/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id, Model model){
        reviewService.deleteReview(id);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable Long id, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(id);
        model.addAttribute("review", reviewDto);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable Long id, @ModelAttribute("review") ReviewDto reviewDto) {
        reviewDto.setUserId(id);
        reviewService.updateReview(reviewDto, id);
        return "redirect:/index";
    }

}
