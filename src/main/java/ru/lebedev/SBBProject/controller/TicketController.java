package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.lebedev.SBBProject.dto.PassengerDTO;
import ru.lebedev.SBBProject.dto.SearchTicketAttributes;
import ru.lebedev.SBBProject.dto.TicketDTO;
import ru.lebedev.SBBProject.model.Ticket;
import ru.lebedev.SBBProject.service.PassengerService;
import ru.lebedev.SBBProject.service.TicketService;
import ru.lebedev.SBBProject.service.TimerService;
import ru.lebedev.SBBProject.service.TrainService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/ticket")
@SessionAttributes("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TrainService trainService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private TimerService timerService;

    @ModelAttribute("ticket")
    public TicketDTO ticketDTO() {
        return new TicketDTO();
    }

    @PostMapping("/processSearchTicket")
    public String processTicketSearch(@ModelAttribute("searchTicketAttr") @Valid SearchTicketAttributes searchTicketAttributes,
                                      BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        List<TicketDTO> tickets = ticketService.findTicket(searchTicketAttributes);
        model.addAttribute("tickets", tickets);
        model.addAttribute("ticketDTO", new TicketDTO());
        return "index";
    }

    @PostMapping("/booking")
    public String trainBooking(@ModelAttribute("ticketDTO") TicketDTO ticket, @ModelAttribute("ticket") TicketDTO ticketDTO, RedirectAttributes redirectAttributes) {
        timerService.seatsBooking(ticket.getTrain());
        ticketDTO = ticket;
        redirectAttributes.addFlashAttribute("ticketDTO", ticketDTO);
        return "redirect:/ticket/fillPassenger";
    }

    @GetMapping("/fillPassenger")
    public String showPassengerForm(@ModelAttribute("ticket") TicketDTO ticket, Model model) {
        model.addAttribute("passenger", new PassengerDTO());
        return "passenger-form";
    }

    @PostMapping("/checkAvailableSeats")
    public @ResponseBody
    String checkAvailableSeats(@RequestBody String trainNumber) {
        return trainService.getAvailableSeats(trainNumber);
    }

    @PostMapping("/processPassengerForm")
    public String processPassengerForm(Principal principal, @ModelAttribute("passenger") @Valid PassengerDTO passenger, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ticketDTO", passenger.getTicketDTO());
            return "passenger-form";
        }

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

        if (timerService.isEnd()) {
            model.addAttribute("error", "Время оформления билета закончилось");
            return "ticket-error";
        }
        ticketService.buyTicket(username, passenger);
        timerService.getTimer().cancel();
        return "ticket-success";
    }

    @GetMapping("/getTickets")
    public String getUserTickets(Principal principal, Model model) {
        List<Ticket> tickets = ticketService.getUserTickets(principal);
        model.addAttribute("tickets", tickets);
        return "user-tickets";
    }
}