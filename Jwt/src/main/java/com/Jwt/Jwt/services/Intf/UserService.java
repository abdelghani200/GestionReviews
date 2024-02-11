package com.Jwt.Jwt.services.Intf;

import com.Jwt.Jwt.Dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
