package com.macor.practicaretrofit.api.model;

public class Commands {

    private String name;
    private int number;

    public Commands(String name, int number) {
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
