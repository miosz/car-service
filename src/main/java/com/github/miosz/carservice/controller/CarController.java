package com.github.miosz.carservice.controller;

import com.github.miosz.carservice.repository.Cars;
import com.github.miosz.carservice.service.CarService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    final Cars cars;
    final CarService carService;

    public CarController(Cars cars, CarService carService) {
        this.cars = cars;
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carService.getCarsToFix());
        return "cars";
    }

    @GetMapping("/cars/add")
    public String getAddCars() {
        return "cars-add";
    }

    @GetMapping("/cars/fix")
    public String getFixCars(Model model) {
        model.addAttribute("cars", carService.getCarsToFix());
        return "cars-fix";
    }

    @GetMapping("/cars/fixed")
    public String getFixedCars(Model model) {
        model.addAttribute("cars", carService.getFixedCars());
        return "cars-fixed";
    }
}
