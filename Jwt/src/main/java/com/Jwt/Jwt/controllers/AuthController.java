package com.Jwt.Jwt.controllers;

import com.Jwt.Jwt.Dto.AuthResponse;
import com.Jwt.Jwt.Dto.UserDTO;
import com.Jwt.Jwt.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUser = authService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody UserDTO userDTO) {
        AuthResponse authResponse = authService.authenticateUser(userDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
