package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.model.Train;
import ru.lebedev.SBBProject.service.employee.RouteManagementService;
import ru.lebedev.SBBProject.service.employee.TimetableManagementService;

import java.util.List;

@Controller
@RequestMapping("/employee/timetable")
public class TimetableManagementController {

    @Autowired
    private TimetableManagementService timetableService;
    @Autowired
    private RouteManagementService routeService;

    @GetMapping("/addTimetable")
    public String getAvailableTrains(Model model) {
        List<Train> trains = timetableService.getAvailableTrains();
        List<RouteNumber> routeNumbers = routeService.getAllRouteNumbers();
        model.addAttribute("trains", trains);
        model.addAttribute("routes", routeNumbers);
        return "create-timetable";
    }

    @PostMapping("/processTimetable")
    public @ResponseBody void addInTimetable(@RequestBody String timetableInfo) {
        timetableService.addInTimetable(timetableInfo);
    }
}
