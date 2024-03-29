package com.GestionReviews.GestionReviews.service.impl;

import com.GestionReviews.GestionReviews.exception.ReviewException;
import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.model.entity.Review;
import com.GestionReviews.GestionReviews.model.dto.respDto.ReviewRespDto;
import com.GestionReviews.GestionReviews.repository.ReviewRepository;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        Review savedReview = reviewRepository.save(review);
        return modelMapper.map(savedReview, ReviewDto.class);
    }

    @Override
    public List<ReviewRespDto> getAll() {
        List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.ASC, "reviewId"));
        return reviewList.stream()
                .map(review -> modelMapper.map(review, ReviewRespDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public ReviewDto update(Long reviewId, ReviewDto reviewDto) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        existingReview.setTitle(reviewDto.getTitle());
        existingReview.setMessage(reviewDto.getMessage());
        existingReview.setDate(reviewDto.getDate());

        Review updatedReview = reviewRepository.save(existingReview);

        return modelMapper.map(updatedReview, ReviewDto.class);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto, Long id) {
        Optional<Review> existingLevelOptional = reviewRepository.findById(id);
        if (existingLevelOptional.isEmpty()) {
            throw new ReviewException("Review not found with ID: " + id);
        }

        Review existingReview = existingLevelOptional.get();
        modelMapper.map(reviewDto, existingReview);
        Review updatedLevel = reviewRepository.save(existingReview);
        return modelMapper.map(updatedLevel, ReviewDto.class);
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            return modelMapper.map(reviewOptional.get(), ReviewDto.class);
        } else {
            throw new ReviewException("Review not found with ID: " + id);
        }
    }

    @Override
    public void signalerReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewException("not found !!"));
        review.setSignalee(true);
        reviewRepository.save(review);
    }
    @Override
    public ReviewDto signalReview(Long reviewId) {
        ReviewDto reviewDto = getReviewById(reviewId);
        reviewDto.setSignalee(true);
        return updateReview(reviewDto, reviewId);
    }

    @Override
    public ReviewDto updateReaction(Long reviewId, String reaction) {
        ReviewDto reviewDto = getReviewById(reviewId);

        reviewDto.setReaction(reaction);

        return updateReview(reviewDto, reviewId);
    }

}
