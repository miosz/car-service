package com.github.miosz.carservice.controller;

import com.github.miosz.carservice.model.Car;
import com.github.miosz.carservice.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carService.getCarsToFix());
        return "cars";
    }

    @GetMapping("/cars/add")
    public String getAddCars(Model model) {
        model.addAttribute("car", new Car());
        return "cars-add-form";
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

    @PostMapping("/cars/add")
    public String postAddCars(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "cars-add-form";
        }
        return "cars-add-form-success";
    }
}
