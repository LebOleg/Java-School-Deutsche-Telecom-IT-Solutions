package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.service.TicketService;
import ru.lebedev.SBBProject.service.TrainService;

import java.util.List;


@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TrainService trainService;

    @GetMapping("/searchTicket")
    public String showTicketSearchForm(Model model) {
        model.addAttribute("searchTicketAttr", new SearchTicketAttributes());
        return "search-ticket";
    }

    @PostMapping("/processSearchTicket")
    public String processTicketSearch(@ModelAttribute("searchTicketAttr") SearchTicketAttributes searchTicketAttributes, Model model) {
        List<Ticket> tickets = ticketService.findTicket(searchTicketAttributes);
        model.addAttribute("tickets", tickets);
        model.addAttribute("ticketDTO", new TicketDTO());
        return "search-ticket";
    }

    @PostMapping("/fillPassenger")
    public String showPassengerForm(@ModelAttribute("ticketDTO") TicketDTO ticket, Model model) {
        return "passenger-form";
    }

    @PostMapping("/checkAvailableSeats")
    public @ResponseBody
    Integer checkAvailableSeats(@RequestBody String trainNumber) {
        return trainService.getAvailableSeats(trainNumber);
    }
}