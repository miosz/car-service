package com.github.miosz.carservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miosz.carservice.model.Car;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class Cars {

    private static final String SRC_MAIN_RESOURCES_DATABASE = "src/main/resources/database/";
    private List<Car> cars;
    private List<Car> fixedCars;
    private ObjectMapper objectMapper;

    public Cars(List<Car> cars, List<Car> fixedCars, ObjectMapper objectMapper) {
        this.cars = cars;
        this.fixedCars = fixedCars;
        this.objectMapper = objectMapper;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Car> getFixedCars() {
        return fixedCars;
    }

    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>(cars);
        allCars.addAll(fixedCars);
        return allCars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addNewCar(Car car) {
        cars.add(car);
    }

    public void removeFixedCar(Car car) {
        cars.remove(car);
    }

    public void saveCarsToFixToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(SRC_MAIN_RESOURCES_DATABASE + "cars-to-fix.json"), cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFixedCarToFile(List<Car> fixedCars) {
        LocalDateTime now = LocalDateTime.now();
        String nowIsoDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Car> carFixedToday = fixedCars.stream().filter(car -> car.getFixedDate().isEqual(now.toLocalDate())).collect(Collectors.toList());
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(SRC_MAIN_RESOURCES_DATABASE + "fixed/" + nowIsoDateTime + ".json"), carFixedToday);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCarsToFixFromFile() {
        try {
            cars = objectMapper.readValue(new File(SRC_MAIN_RESOURCES_DATABASE + "cars-to-fix.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFixedCarsFromDirectory() {
        String directory = SRC_MAIN_RESOURCES_DATABASE + "fixed/";
        fixedCars.clear();

        List<String> filesInDirectory = Stream.of(Objects.requireNonNull(new File(directory).listFiles())).filter(file -> !file.isDirectory()).map(File::getName).collect(Collectors.toList());

        for (String file : filesInDirectory) {
            try {
                fixedCars.addAll(objectMapper.readValue(new File(directory + file), new TypeReference<>() {
                }));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
