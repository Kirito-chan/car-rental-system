package com.carrental.system.application.port.in;

import com.carrental.system.application.domain.model.Car;

import java.util.List;

public interface GetCustomersRentedCarsUseCase {
    List<Car> getRentedCarsByCustomerId(Long customerId);
}
