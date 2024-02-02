package com.GestionReviews.GestionReviews.service.impl;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.model.entity.Review;
import com.GestionReviews.GestionReviews.model.dto.respDto.ReviewRespDto;
import com.GestionReviews.GestionReviews.repository.ReviewRepository;
import com.GestionReviews.GestionReviews.service.interfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<Review> reviewList = reviewRepository.findAll();
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
}
