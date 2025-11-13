package org.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            String login = principal.getAttribute("login"); // For GitHub
            
            // For GitHub, the name might be in the 'login' attribute
            if (name == null && login != null) {
                name = login;
            }
            
            model.addAttribute("userName", name);
            model.addAttribute("userEmail", email);
            model.addAttribute("userAttributes", principal.getAttributes());
        }
        return "index";
    }
}
