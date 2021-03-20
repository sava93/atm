package com.example.atm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;


@AllArgsConstructor
@Data
public class Hours {

    private String hourFrom;
    private String hourTo;


}
