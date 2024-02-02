package com.GestionReviews.GestionReviews.model.dto.respDto;

import com.GestionReviews.GestionReviews.model.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRespDto {
    private Long reviewId;

    private LocalDate date;

    private String title;

    private String message;

    private String reaction;

    private UserDTO user;
}
