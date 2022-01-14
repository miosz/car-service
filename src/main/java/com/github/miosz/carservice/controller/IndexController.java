package com.github.miosz.carservice.controller;

import com.github.miosz.carservice.repository.Cars;
import com.github.miosz.carservice.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CarService carService;

    public IndexController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        carService.loadFromFile();
        model.addAttribute("cars", carService.getCars());
        return "index";
    }
}
