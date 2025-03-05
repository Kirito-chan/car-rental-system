package com.carrental.system.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarCurrentlyRentedException extends RuntimeException {

    public CarCurrentlyRentedException(Long customerId) {
        super("Car with ID " + customerId + " cannot be deleted because it is currently rented by a customer.");
    }
}
