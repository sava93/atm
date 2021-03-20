package com.example.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtmLocator {


    private Address address;
    private int distance;
    private List<OpeningHours> openingHours;
    private String functionality;
    private Type type;



}
