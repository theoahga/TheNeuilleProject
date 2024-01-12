package com.theoahga.model;

public class FireType {
    private String name;

    private int number;

    public FireType(String name, int number) {
        this.name = name;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
