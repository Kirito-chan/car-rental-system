package com.carrental.system.application.port.in;


import com.carrental.system.application.domain.model.Car;

import java.util.List;

public interface CarUseCase {

    Car getCar(Long carId);

    List<Car> getAllCars();

    List<Car> getAllRentedCars();

    Long getTotalKilometersDriven(Long carId);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCar(Long carId);
}
