package com.carrental.system.application.domain.service;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.in.CarUseCase;
import com.carrental.system.application.port.out.CarPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CarService implements CarUseCase {
    private final CarPort carPort;

    @Override
    public Car getCar(Long carId) {
        return carPort.getCar(carId);
    }

    @Override
    public List<Car> getAllCars(PageDefinition pageDefinition) {
        return carPort.getAllCars(pageDefinition);
    }

    @Override
    public Long getTotalKilometersDriven(Long carId) {
        return carPort.getTotalKilometersDriven(carId);
    }

    @Override
    public Car createCar(Car car) {
        return carPort.createCar(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carPort.updateCar(car);
    }

    @Override
    public void deleteCar(Long carId) {
        carPort.deleteCar(carId);
    }
}
