package com.GestionReviews.GestionReviews.service.interfaces;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;
import com.GestionReviews.GestionReviews.model.dto.respDto.ReviewRespDto;

import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto);
    List<ReviewRespDto> getAll();
    ReviewDto updateReview(ReviewDto reviewDto, Long id);
    ReviewDto getReviewById(Long id);
    void deleteReview(Long id);
    ReviewDto update(Long reviewId, ReviewDto reviewDto);

    void signalerReview(Long id);
    ReviewDto signalReview(Long reviewId);

}
