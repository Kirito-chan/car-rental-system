package com.carrental.system.application.port.out;

import com.carrental.system.application.domain.model.Car;

import java.util.List;

public interface CarPort {
    Car getCar(Long carId);

    List<Car> getAllCars();

    Long getTotalKilometersDriven(Long carId);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long carId);
}
