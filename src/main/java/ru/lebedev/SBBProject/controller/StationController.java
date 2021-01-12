package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.model.Timetable;
import ru.lebedev.SBBProject.service.StationService;

import java.util.List;

@Controller
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/searchStation")
    public String getStationSearch(Model model) {
        model.addAttribute("stationName", "");
        return "autocomplete-search";
    }

    @PostMapping("/autofill")
    public @ResponseBody
    String getAutoFilledStation(@RequestBody String query) {
        return stationService.getAutofilledStation(query);
    }

    @PostMapping("/showTimetable")
    public String showStationTimetable(@ModelAttribute("stationName") String station, Model model) {
        List<Timetable> stationTimetable = stationService.getStationTimetable(station);
        model.addAttribute("timetable", stationTimetable);
        return "autocomplete-search";
    }

}
