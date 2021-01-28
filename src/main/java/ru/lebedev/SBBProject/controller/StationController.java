package ru.lebedev.SBBProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.dto.SearchStationTimetableDTO;
import ru.lebedev.SBBProject.dto.StationTimetableDTO;
import ru.lebedev.SBBProject.service.StationService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("/searchStation")
    public String getStationSearch(Model model) {
        model.addAttribute("searchStation", new SearchStationTimetableDTO());
        return "autocomplete-search";
    }

    @PostMapping("/autofill")
    public @ResponseBody
    String getAutoFilledStation(@RequestBody String query) {
        return stationService.getAutofilledStation(query);
    }

    @PostMapping("/showTimetable")
    public String showStationTimetable(@ModelAttribute("searchStation") @Valid SearchStationTimetableDTO searchStationTimetableDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "autocomplete-search";
        }

        List<StationTimetableDTO> stationTimetable = stationService.getStationTimetable(searchStationTimetableDTO);
        model.addAttribute("timetable", stationTimetable);
        return "autocomplete-search";
    }

}
