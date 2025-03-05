package com.carrental.system.application.port.out;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;

import java.util.List;

public interface CarPort {
    Car getCar(Long carId);

    List<Car> getAllCars(PageDefinition pageDefinition);

    long getTotalKilometersDriven(Long carId);

    Car createCar(Car car);

    Car updateCar(Car car, boolean updateRented);

    void deleteCar(Long carId);
}
