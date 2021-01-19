package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lebedev.SBBProject.dao.StationDAO;
import ru.lebedev.SBBProject.model.Route;
import ru.lebedev.SBBProject.service.StationService;
import ru.lebedev.SBBProject.service.employee.StationManagementService;

import java.util.Optional;

@Controller
public class StationManagementController {

    @Autowired
    private StationManagementService stationService;



    @GetMapping("/getStationCreateForm")
    public String getStationCreateForm(Model model) {
        model.addAttribute("route", new Route());
        return "create-railway";
    }

    @PostMapping("/createStation")
    public @ResponseBody String createStation(@RequestBody String station) {
        return stationService.createStation(station).toString();
    }

    @PostMapping("/createConnection")
    public @ResponseBody String createConnection(@RequestBody String stations) {
        return stationService.createConnection(stations).toString();
    }
}
