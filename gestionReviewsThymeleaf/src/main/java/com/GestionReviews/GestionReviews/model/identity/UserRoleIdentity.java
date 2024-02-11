package com.GestionReviews.GestionReviews.model.identity;

import com.GestionReviews.GestionReviews.model.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleIdentity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}


 */