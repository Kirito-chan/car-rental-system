package com.carrental.system.adapter.in.web.model;

import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;

import java.util.List;

public record CustomerRentalInfoResponse(Customer customer, List<Car> rentedCars) {

}
