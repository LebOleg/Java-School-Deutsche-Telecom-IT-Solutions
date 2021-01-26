package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.dto.PassengerDTO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.service.PassengerService;
import ru.lebedev.SBBProject.service.TicketService;
import ru.lebedev.SBBProject.service.TrainService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/processSearchTicket")
    public String processTicketSearch(@ModelAttribute("searchTicketAttr") SearchTicketAttributes searchTicketAttributes, Model model) {
        List<TicketDTO> tickets = ticketService.findTicket(searchTicketAttributes);
        model.addAttribute("tickets", tickets);
        model.addAttribute("ticketDTO", new TicketDTO());
        return "index";
    }

    @PostMapping("/fillPassenger")
    public String showPassengerForm(@ModelAttribute("ticketDTO") TicketDTO ticket, Model model) {
        model.addAttribute("passenger", new PassengerDTO());
        return "passenger-form";
    }

    @PostMapping("/checkAvailableSeats")
    public @ResponseBody
    String checkAvailableSeats(@RequestBody String trainNumber) {
        return trainService.getAvailableSeats(trainNumber).toString();
    }

    @PostMapping("/processPassengerForm")
    public String processPassengerForm(Principal principal, @ModelAttribute("passenger") PassengerDTO passenger, Model model) {

        if (!ticketService.isAvailableForPurchase(passenger.getTicketDTO())) {
            model.addAttribute("error", "Билет не куплен. До отправления менее 10 минут");
            return "ticket-error";
        }

        Boolean passengerOnTrain = passengerService.passengerOnTrainExists(PassengerDTO.convertToPassenger(passenger));

        if (passengerOnTrain) {
            model.addAttribute("error", "Такой пассажир уже зарегистрирован на данном поезде");
            return "ticket-error";
        }

        String username = principal.getName();
        ticketService.buyTicket(username, passenger);
        return "ticket-success";
    }

    @GetMapping("/getTickets")
    public String getUserTickets(Principal principal, Model model) {
        List<Ticket> tickets = ticketService.getUserTickets(principal);
        model.addAttribute("tickets", tickets);
        return "user-tickets";
    }
}