package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.model.Route;
import ru.lebedev.SBBProject.service.employee.RouteManagementService;

@Controller
@RequestMapping("/employee/route")
public class RouteManagementController {
    @Autowired
    private RouteManagementService routeService;

    @PostMapping("/showCreateRoute")
    public String showCreateRouteForm(@ModelAttribute Route route, Model model) {

        model.addAttribute(route);
        return "create-route";

    }

    @PostMapping("/addPath")
    public @ResponseBody String addPathToRoute(@RequestBody String path) {

        return routeService.createPathInRoute(path).toString();
    }

    @GetMapping("/getRouteNumberForm")
    public String getRouteNumberForm(Model model) {
        model.addAttribute("route", new Route());
        return "create-route-number";
    }
}
