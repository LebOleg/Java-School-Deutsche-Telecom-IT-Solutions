package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.model.Timetable;
import ru.lebedev.SBBProject.service.TicketService;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/searchTicket")
    public String showTicketSearchForm(Model model) {
        model.addAttribute("searchTicketAttr", new SearchTicketAttributes());
        return "search-ticket";
    }

    @PostMapping("/processSearchTicket")
    public String processTicketSearch(@ModelAttribute("searchTicketAttr") SearchTicketAttributes searchTicketAttributes, Model model) {
        Map<Timetable, LocalDateTime> availableTicket = ticketService.findTicket(searchTicketAttributes);
        model.addAttribute("tickets", availableTicket);
        return "search-ticket";
    }


}