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

    public void removeFixedCar(Car car) {
        cars.removeFixedCar(car);
    }

    public void saveCarsToFixToFile() {
        cars.saveCarsToFixToFile();
    }

    public void loadCarsToFixFromFile() {
        cars.loadCarsToFixFromFile();
    }

    public void loadFixedCarsFromDirectory() {
        cars.loadFixedCarsFromDirectory();
    }

    public List<Car> getCarsToFix() {
        return cars.getCars().stream().filter(c -> !c.isFixed()).collect(Collectors.toList());
    }

    public List<Car> getFixedCars() {
        return cars.getFixedCars();
    }

    public List<Car> getAllCars() {
        return cars.getAllCars();
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

    public void saveFixedCarToFile(List<Car> fixedCars) {
        cars.saveFixedCarToFile(fixedCars);
    }
}
