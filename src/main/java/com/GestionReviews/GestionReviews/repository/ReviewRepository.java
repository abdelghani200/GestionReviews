package com.GestionReviews.GestionReviews.repository;

import com.GestionReviews.GestionReviews.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
