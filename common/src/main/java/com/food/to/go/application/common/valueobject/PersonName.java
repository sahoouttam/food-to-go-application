package com.food.to.go.application.common.valueobject;

public class PersonName {

    private String firstName;
    private String secondName;

    public PersonName() {
    }

    public PersonName(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
