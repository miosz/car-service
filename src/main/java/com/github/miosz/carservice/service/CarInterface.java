package com.github.miosz.carservice.service;

import com.github.miosz.carservice.model.Car;

import java.util.List;

public interface CarInterface {

    List<Car> getCars();

    void addNewCar(Car car);

    void saveToFile();

    void loadFromFile();

    List<Car> getCarsToFix();

    List<Car> getFixedCars();
}
