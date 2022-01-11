package com.github.miosz.carservice.model;

import com.github.miosz.carservice.enums.Color;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Car {
    @Size(min = 3)
    private String name;
    @Size(min = 5)
    private String registrationNumber;
    @Min(1900)
    @Max(2022)
    private int productionYear;
    private Color color;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate serviceDate;
    private LocalDate fixedDate;
    private boolean isFixed;

    public Car() {
    }

    public Car(String name, String registrationNumber, int productionYear, Color color, LocalDate serviceDate, LocalDate fixedDate, boolean isFixed) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.productionYear = productionYear;
        this.color = color;
        this.serviceDate = serviceDate;
        this.fixedDate = fixedDate;
        this.isFixed = isFixed;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public LocalDate getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(LocalDate fixedDate) {
        this.fixedDate = fixedDate;
    }
}
