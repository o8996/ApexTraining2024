package com.learn.oauth_demo.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, OAuth2AuthenticationToken authentication) {
        // Get user information from the authentication token
        model.addAttribute("name", authentication.getPrincipal().getAttribute("name"));
        model.addAttribute("email", authentication.getPrincipal().getAttribute("email"));
        model.addAttribute("profilePicture", authentication.getPrincipal().getAttribute("picture"));
        return "profile";
    }
}
