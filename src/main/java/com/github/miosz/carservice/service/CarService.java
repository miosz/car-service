package com.github.miosz.carservice.service;

import com.github.miosz.carservice.model.Car;
import com.github.miosz.carservice.repository.Cars;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    final Cars cars;

    public CarService(Cars cars) {
        this.cars = cars;
    }

    public List<Car> getCarsToFix() {
        return cars.getCars().stream().filter(c -> !c.isFixed()).collect(Collectors.toList());
    }

    public List<Car> getFixedCars() {
        return cars.getCars().stream().filter(c -> c.isFixed()).collect(Collectors.toList());
    }
}
