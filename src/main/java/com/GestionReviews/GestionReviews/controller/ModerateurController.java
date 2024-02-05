package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModerateurController {

    private final ReviewService reviewService;

    public ModerateurController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/moderateur/index")
    public String ViewReviews(Model model){
        model.addAttribute("reviews", reviewService.getAll());
        return "moderateur/index";
    }
    @GetMapping("/moderateur/signaler/{reviewId}")
    public String signalerReviewForm(@PathVariable Long reviewId, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        model.addAttribute("review", reviewDto);
        return "moderateur/signaler";
    }

    @PostMapping("/moderateur/signaler/{reviewId}")
    public String signalerReview(@PathVariable Long reviewId) {
        reviewService.signalerReview(reviewId);
        return "redirect:/moderateur/index";
    }

    @GetMapping("/moderateur/edit/{id}")
    public String editReviewForm(@PathVariable Long id, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(id);
        model.addAttribute("review", reviewDto);
        return "moderateur/edit";
    }

    @PostMapping("/moderateur/edit/{id}")
    public String editReview(@PathVariable Long id, @ModelAttribute("review") ReviewDto reviewDto) {
        reviewDto.setUserId(id);
        reviewService.updateReview(reviewDto, id);
        return "redirect:/moderateur/index";
    }

    @GetMapping("/moderateur/delete/{id}")
    public String deleteReview(@PathVariable Long id, Model model){
        reviewService.deleteReview(id);
        return "redirect:/moderateur/index";
    }


}
