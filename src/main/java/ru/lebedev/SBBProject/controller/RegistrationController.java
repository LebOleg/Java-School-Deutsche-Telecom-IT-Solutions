package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.SBBProject.model.User;
import ru.lebedev.SBBProject.service.UserService;
import ru.lebedev.SBBProject.validation.PasswordGroup;

import javax.validation.groups.Default;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {
        theModel.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processForm(@ModelAttribute("user") @Validated(value = {Default.class, PasswordGroup.class}) User user,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        User checkUser = userService.getUser(user.getUsername());

        if (userService.checkIfUsernameExists(checkUser)) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "This user already exists");
            return "registration-form";
        }

        userService.save(user);

        return "registration-confirmation";
    }
}