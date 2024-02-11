package com.GestionReviews.GestionReviews.service.interfaces;

import com.GestionReviews.GestionReviews.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    //loadUserByUsername
    UserDetails loadUserByUsername(String username);

}
