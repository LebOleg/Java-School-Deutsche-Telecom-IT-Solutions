package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.SBBProject.dto.TrainDTO;
import ru.lebedev.SBBProject.model.RouteNumber;
import ru.lebedev.SBBProject.service.employee.RouteManagementService;
import ru.lebedev.SBBProject.service.employee.TrainManagementService;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/employee/train")
public class TrainManagementController {

    @Autowired
    private TrainManagementService trainService;
    @Autowired
    private RouteManagementService routeService;

    @GetMapping("/getCreateTrainForm")
    public String getCreateTrainForm(Model model) {
        List<RouteNumber> routeNumbers = routeService.getAllRouteNumbers();
        model.addAttribute("routes", routeNumbers);

        return "create-train";
    }

    @PostMapping("/createTrain")
    public @ResponseBody
    String createTrains(@RequestBody String trainInfo) {
        String result = trainService.createTrain(trainInfo);

        return URLEncoder.encode(result, StandardCharsets.UTF_8);
    }

    @GetMapping(value = {"", "/{page}"})
    public String showTrains(@PathVariable(required = false, name = "page") String page, HttpServletRequest request) {
        PagedListHolder<TrainDTO> trains;

        if (page == null) {
            trains = new PagedListHolder<>();
            List<TrainDTO> trainList = trainService.getAllTrains();

            trains.setSource(trainList);
            trains.setPageSize(5);
            request.getSession().setAttribute("trains", trains);

        } else if (page.equals("prev")) {
            trains = (PagedListHolder<TrainDTO>) request.getSession().getAttribute("trains");
            trains.previousPage();
        } else if (page.equals("next")) {
            trains = (PagedListHolder<TrainDTO>) request.getSession().getAttribute("trains");
            trains.nextPage();
        } else {
            int pageNum = Integer.parseInt(page);
            trains = (PagedListHolder<TrainDTO>) request.getSession().getAttribute("trains");
            trains.setPage(pageNum - 1);
        }

        return "train-list";
    }
}
