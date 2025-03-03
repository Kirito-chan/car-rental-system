package com.carrental.system.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rental {

    private Long id;
    private Long customerId;
    private Long carId;
}
