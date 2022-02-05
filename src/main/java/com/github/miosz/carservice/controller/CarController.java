package com.github.miosz.carservice.controller;

import com.github.miosz.carservice.model.Car;
import com.github.miosz.carservice.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCarsToRepair(Model model) {
        model.addAttribute("cars", carService.getCarsToFix());
        return "cars";
    }

    @GetMapping("/cars/add")
    public String getAddCars(Model model) {
        model.addAttribute("car", new Car());
        return "cars-add-form";
    }

    @GetMapping("/cars/fix")
    public String getCarsToFix(Model model) {
        model.addAttribute("cars", carService.getCarsToFix());
        return "cars-fix";
    }

    @PostMapping("/cars/fix/{registrationNumber}")
    public String postFixCar(@PathVariable("registrationNumber") String registrationNumber, Model model) {
        Car car = carService.getCarByRegistrationNumber(registrationNumber);
        carService.removeFixedCar(car);
        carService.saveCarsToFixToFile();
        carService.fixCar(car);
        carService.setFixedDate(car);
        carService.getFixedCars().add(car);
        carService.saveFixedCarToFile(carService.getFixedCars());
        model.addAttribute("cars", carService.getCarsToFix());
        return "redirect:/cars/fix";
    }

    @GetMapping("/cars/fixed")
    public String getFixedCars(Model model) {
        carService.loadFixedCarsFromDirectory();
        model.addAttribute("cars", carService.getFixedCars());
        return "cars-fixed";
    }

    @PostMapping("/cars/add")
    public String postAddCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "cars-add-form";
        }
        carService.addNewCar(car);
        carService.saveCarsToFixToFile();
        model.addAttribute("cars", carService.getCars());
        return "cars-add-form-success";
    }
}
