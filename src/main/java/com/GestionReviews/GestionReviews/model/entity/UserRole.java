package com.GestionReviews.GestionReviews.model.entity;

import com.GestionReviews.GestionReviews.model.identity.UserRoleIdentity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userRoles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRoleIdentity id;

    @MapsId("user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("role")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
