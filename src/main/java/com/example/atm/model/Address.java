package com.example.atm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private String street;
    @JsonProperty(value = "housenumber")
    private String houseNumber;
    @JsonProperty(value = "postalcode")
    private String postalCode;
    private String city;
    private GeoLocation geoLocation;

}
