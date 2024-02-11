package com.Jwt.Jwt.entities;

import com.Jwt.Jwt.enumeration.ReactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @NotNull(message = "Date is required")
    @Column(name = "date", nullable = false, length = 250)
    private LocalDate date;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 250)
    private String title;

    @NotBlank(message = "Message is required")
    @Column(name = "message", nullable = false, length = 400)
    private String message;

    @NotNull(message = "Reaction is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "reaction", nullable = false, length = 25)
    private ReactionType reaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "signalee")
    private Boolean signalee;





}

