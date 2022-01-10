package com.github.miosz.carservice.controller;

import com.github.miosz.carservice.repository.Cars;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final Cars cars;

    public IndexController(Cars cars) {
        this.cars = cars;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("cars", cars.getCars());
        return "index";
    }
}
