package com.github.miosz.carservice.service;

import com.github.miosz.carservice.model.Car;
import com.github.miosz.carservice.repository.Cars;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements CarInterface {
    final Cars cars;

    public CarService(Cars cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        return cars.getCars();
    }

    public void addNewCar(Car car) {
        cars.addNewCar(car);
    }

    public void saveToFile() {
        cars.saveToFile();
    }

    public void loadFromFile() {
        cars.loadFromFile();
    }

    public List<Car> getCarsToFix() {
        return cars.getCars().stream().filter(c -> !c.isFixed()).collect(Collectors.toList());
    }

    public List<Car> getFixedCars() {
        return cars.getCars().stream().filter(c -> c.isFixed()).collect(Collectors.toList());
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        return cars.getCars().stream().filter(c -> c.getRegistrationNumber().equals(registrationNumber)).findFirst().get();
    }

    public void fixCar(Car car) {
        car.setFixed(true);
    }

    public void setFixedDate(Car car) {
        car.setFixedDate(LocalDate.now());
    }
}
