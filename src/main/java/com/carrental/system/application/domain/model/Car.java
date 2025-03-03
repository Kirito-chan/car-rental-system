package com.carrental.system.application.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private Long id;
    private String make;
    private String model;
    private Integer totalSeats;
    private boolean isAutomaticTransmission;
    private boolean isRented;
    private Long totalKilometersDriven;
}
