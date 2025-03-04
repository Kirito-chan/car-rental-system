package com.carrental.system.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerCurrentlyRentingException extends RuntimeException {

    public CustomerCurrentlyRentingException(Long customerId) {
        super("Customer with ID " + customerId + " cannot be deleted because they are currently renting a car.");
    }
}
