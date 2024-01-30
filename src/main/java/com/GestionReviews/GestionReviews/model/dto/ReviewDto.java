package com.GestionReviews.GestionReviews.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long reviewId;

    @Column(name = "date", nullable = false, length = 50)
    private LocalDate date;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "message", nullable = false, length = 50)
    private String message;

    @Column(name = "reaction", nullable = false, length = 50)
    private String reaction;
}
