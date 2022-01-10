package com.github.miosz.carservice.enums;

public enum Color {
    WHITE("White"),
    BLACK("Black"),
    GRAY("Gray"),
    SILVER("Silver"),
    RED("Red"),
    BLUE("Blue"),
    BROWN("Brown"),
    GREEN("Green"),
    BEIGE("Beige"),
    ORANGE("Orange"),
    GOLD("Gold"),
    YELLOW("Yellow"),
    PURPLE("Purple");

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
