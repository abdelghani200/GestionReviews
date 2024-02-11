package com.Jwt.Jwt.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReviewRespDto {
    private Long reviewId;
    @NotNull(message = "Date is required")
    private LocalDate date;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Message is required")
    private String message;
    @NotNull(message = "Reaction is required")
    private String reaction;
    private Boolean signalee;
    @NotNull(message = "User is required")
    private UserDTO user;
}
