package com.GestionReviews.GestionReviews.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class RedirectController {
    @GetMapping("/redirectByRole")
    public String redirectByRole(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/index";
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MODERATOR"))) {
            return "redirect:/moderateur/index";
        } else {
            return "redirect:/";
        }
    }

}
