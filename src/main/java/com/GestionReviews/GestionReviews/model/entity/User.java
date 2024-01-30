package com.GestionReviews.GestionReviews.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false, length = 1000, unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
}
