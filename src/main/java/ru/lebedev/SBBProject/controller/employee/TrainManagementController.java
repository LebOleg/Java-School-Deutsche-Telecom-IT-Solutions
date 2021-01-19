package ru.lebedev.SBBProject.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lebedev.SBBProject.service.employee.TrainManagementService;

@Controller
public class TrainManagementController {

    @Autowired
    private TrainManagementService trainService;

    @GetMapping("/getCreateTrainForm")
    public String getCreateTrainForm() {

        return "create-train";
    }

    @PostMapping("/createTrain")
    public @ResponseBody String createTrains(@RequestBody String trainInfo) {

        return trainService.createTrain(trainInfo).toString();
    }
}
