package com.Jwt.Jwt.security;

import com.Jwt.Jwt.Dto.AuthResponse;
import com.Jwt.Jwt.Dto.UserDTO;
import com.Jwt.Jwt.enumeration.RoleType;
import com.Jwt.Jwt.repositories.UserRepository;
import com.Jwt.Jwt.services.Intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    public AuthResponse authenticateUser(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails, userDetails.getAuthorities().toString());

        return new AuthResponse(token);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        com.Jwt.Jwt.entities.User newUser = new com.Jwt.Jwt.entities.User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRole(RoleType.valueOf(userDTO.getRole()));
        userRepository.save(newUser);

        return userDTO;
    }





}
