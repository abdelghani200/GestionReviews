package com.Jwt.Jwt.services.Intf;

import com.Jwt.Jwt.Dto.ReviewDto;
import com.Jwt.Jwt.Dto.ReviewRespDto;

import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto);
    List<ReviewRespDto> getAll();
    ReviewDto updateReview(ReviewDto reviewDto, Long id);
    ReviewDto getReviewById(Long id);
    void deleteReview(Long id);
    ReviewDto update(Long reviewId, ReviewDto reviewDto);

    void signalerReview(Long id);
}
