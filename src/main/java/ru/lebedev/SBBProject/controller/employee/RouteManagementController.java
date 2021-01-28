package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.model.Route;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.service.employee.RouteManagementService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee/route")
public class RouteManagementController {
    @Autowired
    private RouteManagementService routeService;

    @PostMapping("/showCreateRoute")
    public String showCreateRouteForm(@ModelAttribute("route") @Valid RouteNumber route, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create-route-number";
        }

        model.addAttribute(route);
        return "create-route";

    }

    @PostMapping("/addPath")
    public @ResponseBody
    String addPathToRoute(@RequestBody String path) {
        return routeService.createPathInRoute(path);
    }

    @GetMapping("/getRouteNumberForm")
    public String getRouteNumberForm(Model model) {
        model.addAttribute("route", new RouteNumber());
        return "create-route-number";
    }

    @GetMapping(value = {"/getRoutes", "/getRoutes/{number}"})
    public String getAllRoutes(HttpServletRequest request, @PathVariable(required = false, name = "number") String route) {
        List<RouteNumber> routes = routeService.getAllRouteNumbers();
        request.getSession().setAttribute("routes", routes);

        if (route != null) {
            List<Route> stationOnRout = routeService.getRouteByNumber(route);
            request.getSession().setAttribute("routesStation", stationOnRout);
        }

        return "routes";
    }
}
