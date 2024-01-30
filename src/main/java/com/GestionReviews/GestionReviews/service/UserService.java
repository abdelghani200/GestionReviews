package com.GestionReviews.GestionReviews.service;

import com.GestionReviews.GestionReviews.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    List<UserDTO> getAllUsers();

}
