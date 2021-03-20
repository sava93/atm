package com.example.atm.model;

public enum Type {

    IGN("IGN"),
    GELDMAAT("GELDMAAT"),
    ING("ING");


    private final String value;

    Type(String value) {
        this.value = value;
    }

}
