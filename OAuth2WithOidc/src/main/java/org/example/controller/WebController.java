package org.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OidcUser principal, Model model) {
        model.addAttribute("name", principal.getFullName());
        model.addAttribute("email", principal.getEmail());
        model.addAttribute("avatar", principal.getAttribute("picture"));
        model.addAttribute("emailVerified", principal.getEmailVerified());
        model.addAttribute("idToken", principal.getIdToken().getTokenValue());
        return "user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
