package com.Jwt.Jwt.services.Impl;

import com.Jwt.Jwt.Dto.UserDTO;
import com.Jwt.Jwt.services.Intf.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO create(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }
}
