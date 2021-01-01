package ru.lebedev.SBBProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lebedev.SBBProject.model.User;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model model) {

        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
