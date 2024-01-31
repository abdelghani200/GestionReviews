package com.GestionReviews.GestionReviews.controller;

import com.GestionReviews.GestionReviews.model.dto.UserDTO;
import com.GestionReviews.GestionReviews.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "create";
    }


    @PostMapping("/create")
    public String processRegistration(UserDTO userDTO, Model model) {
        userService.create(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";


    }
}
