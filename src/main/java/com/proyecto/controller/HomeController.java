package com.tuapp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            // Si el usuario está autenticado
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", userDetails.getUsername());
        } else {
            // Si el usuario no está autenticado
            model.addAttribute("isLoggedIn", false);
        }
        return "index"; // Nombre del archivo Thymeleaf a cargar (por ejemplo, index.html)
    }
}
