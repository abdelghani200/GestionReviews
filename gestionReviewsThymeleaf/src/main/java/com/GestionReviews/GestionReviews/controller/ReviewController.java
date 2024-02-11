package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.exception.ReviewException;
import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.model.entity.User;
import com.GestionReviews.GestionReviews.repository.UserRepository;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            String loggedInUsername = authentication.getName();
            model.addAttribute("loggedInUsername", loggedInUsername);

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
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminIndexPage(Model model){
        model.addAttribute("listReviews", reviewService.getAll());
        return "admin/indx";
    }

    @GetMapping("/reviews/{reviewId}/updateReaction")
    public String updateReactionForm(@PathVariable("reviewId") Long reviewId, Model model) {
        ReviewDto reviewDto = reviewService.getReviewById(reviewId);
        model.addAttribute("review", reviewDto);
        return "/";
    }
    /*
    @PostMapping("/reviews/{reviewId}/updateReaction")
    public String updateReaction(
            @PathVariable("reviewId") Long reviewId,
            @RequestParam("reaction") String reaction,
            RedirectAttributes redirectAttributes
    ) {
        try {
            ReviewDto updatedReview = reviewService.updateReaction(reviewId, reaction);
            redirectAttributes.addFlashAttribute("successMessage", "Reaction updated successfully.");
        } catch (ReviewException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

     */
    @PostMapping("/reviews/{reviewId}/updateReaction")
    public String updateReaction(
            @PathVariable("reviewId") Long reviewId,
            @RequestParam("reaction") String reaction,
            RedirectAttributes redirectAttributes
    ) {
        try {
            reviewService.updateReaction(reviewId, reaction); // Update the reaction
            redirectAttributes.addFlashAttribute("successMessage", "Reaction updated successfully.");
        } catch (ReviewException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }


}
