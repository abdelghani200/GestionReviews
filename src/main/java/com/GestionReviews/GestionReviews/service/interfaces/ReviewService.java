package com.GestionReviews.GestionReviews.service.interfaces;

import com.GestionReviews.GestionReviews.model.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto save(ReviewDto reviewDto);
    List<ReviewDto> getAll();
}
