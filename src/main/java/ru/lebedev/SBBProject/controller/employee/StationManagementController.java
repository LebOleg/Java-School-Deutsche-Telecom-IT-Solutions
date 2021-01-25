package ru.lebedev.SBBProject.controller.employee;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.service.employee.RailwayManagementService;
import ru.lebedev.SBBProject.service.employee.StationManagementService;

@Controller
@RequestMapping("/employee/station")
public class StationManagementController {

    @Autowired
    private StationManagementService stationService;
    @Autowired
    private RailwayManagementService railwayService;

    @GetMapping("/showRailway")
    public String getSigmaTest(Model model) {
        JSONObject jsonGraph = railwayService.getRailwayGraph();
        model.addAttribute("graph", jsonGraph.toString());
        return "graph";
    }

    @GetMapping("/getStationCreateForm")
    public String getStationCreateForm() {
        return "create-railway";
    }

    @PostMapping("/createStation")
    public @ResponseBody
    String createStation(@RequestBody String station) {
        return stationService.createStation(station).toString();
    }

    @PostMapping("/createConnection")
    public @ResponseBody
    String createConnection(@RequestBody String stations) {
        return stationService.createConnection(stations).toString();
    }
}
