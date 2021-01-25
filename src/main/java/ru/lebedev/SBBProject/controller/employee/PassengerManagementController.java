package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.SBBProject.model.Passenger;
import ru.lebedev.SBBProject.service.employee.PassengerManagementService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee/passengers/")
public class PassengerManagementController {

    @Autowired
    private PassengerManagementService passengerService;

    @GetMapping(value = {"/getPassengers/{train}", "/getPassengers/{train}/{page}"})
    public String getPassengersOnTrain(@PathVariable("train") String trainNumber,
                                       @PathVariable(required = false, name = "page") String page,
                                       HttpServletRequest request) {

        PagedListHolder<Passenger> pagePassenger;

        if (page == null) {
            pagePassenger = new PagedListHolder<>();
            List<Passenger> passengers = passengerService.getPassengersOnTrain(trainNumber);

            pagePassenger.setSource(passengers);
            pagePassenger.setPageSize(10);
            request.getSession().setAttribute("passengers", pagePassenger);

        } else if (page.equals("prev")) {
            pagePassenger = (PagedListHolder<Passenger>) request.getSession().getAttribute("passengers");
            pagePassenger.previousPage();

        } else if (page.equals("next")) {
            pagePassenger = (PagedListHolder<Passenger>) request.getSession().getAttribute("passengers");
            pagePassenger.nextPage();

        } else {
            int pageNum = Integer.parseInt(page);
            pagePassenger = (PagedListHolder<Passenger>) request.getSession().getAttribute("passengers");
            pagePassenger.setPage(pageNum - 1);
        }

        return "train-passengers";

    }
}
