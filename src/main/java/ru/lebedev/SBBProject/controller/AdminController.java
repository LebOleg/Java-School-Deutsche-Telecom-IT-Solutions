package ru.lebedev.SBBProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class AdminController {
    @GetMapping("/info")
    public String getSomeAdminPage() {

        return "admin-page";
    }
}
