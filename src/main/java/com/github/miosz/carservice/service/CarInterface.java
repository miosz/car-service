package com.github.miosz.carservice.service;

import com.github.miosz.carservice.model.Car;

import java.util.List;

public interface CarInterface {

    List<Car> getCars();

    void addNewCar(Car car);

    void removeFixedCar(Car car);

    void saveCarsToFixToFile();

    void loadCarsToFixFromFile();

    List<Car> getCarsToFix();

    List<Car> getFixedCars();
}
