package com.github.miosz.carservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.miosz.carservice.enums.Color;
import com.github.miosz.carservice.model.Car;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Cars {

    private List<Car> cars;
    private ObjectMapper objectMapper;

    public Cars(List<Car> cars) {
        this.cars = cars;
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> restoreDatabase() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Kia", "GD123AB", 2017, Color.WHITE, LocalDate.parse("2022-01-01"), null, false));
        carList.add(new Car("Skoda", "GA456CD", 2018, Color.BLACK, LocalDate.parse("2022-01-01"), LocalDate.now(), true));
        carList.add(new Car("Opel", "GS789EF", 2019, Color.GRAY, LocalDate.parse("2022-01-01"), null, false));
        return carList;
    }

    public void addNewCar(Car car) {
        cars.add(car);
    }

    public void saveToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/database/cars.json"), cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            cars.clear();
            cars = objectMapper.readValue(new File("src/main/resources/database/cars.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
