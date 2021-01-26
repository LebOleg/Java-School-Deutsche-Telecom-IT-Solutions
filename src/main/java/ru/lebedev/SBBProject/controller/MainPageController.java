package ru.lebedev.SBBProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("searchTicketAttr", new SearchTicketAttributes());
        return "index";
    }
}
