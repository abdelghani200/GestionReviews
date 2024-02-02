package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.model.entity.User;
import com.GestionReviews.GestionReviews.repository.UserRepository;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;



    public ReviewController(ReviewService reviewService, UserRepository userRepository) {
        this.reviewService = reviewService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String viewMainPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);

            Long userId = getUserIdByUsername(username);
            model.addAttribute("userId", userId);
        }

        model.addAttribute("reviewDto", new ReviewDto());

        model.addAttribute("listReviews", reviewService.getAll());

        return "index";
    }
    private Long getUserIdByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.getUserId() : null;
    }


    @PostMapping("/")
    public String submitReview(@ModelAttribute ReviewDto reviewDto) {
        reviewDto.setReaction("LIKE");
        reviewDto.setDate(LocalDate.now());

        reviewService.save(reviewDto);
        return "redirect:/";
    }
}
