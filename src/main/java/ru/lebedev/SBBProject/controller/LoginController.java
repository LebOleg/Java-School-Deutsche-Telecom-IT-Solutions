package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lebedev.SBBProject.model.User;
import ru.lebedev.SBBProject.service.UserService;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model model) {

        if(userService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }
}
