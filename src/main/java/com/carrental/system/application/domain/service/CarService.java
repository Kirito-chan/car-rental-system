package com.carrental.system.application.domain.service;

import com.carrental.system.adapter.in.web.model.CarRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.port.in.CarCrudUseCases.*;
import com.carrental.system.application.port.in.GetTotalDistanceRentedForCarUseCase;
import com.carrental.system.application.port.out.CarPort;
import com.carrental.system.application.port.out.RentalPort;
import com.carrental.system.common.exception.CarCurrentlyRentedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CarService implements CreateCarUseCase, GetCarUseCase, GetAllCarsUseCase, UpdateCarUseCase, DeleteCarUseCase, GetTotalDistanceRentedForCarUseCase {
    private final CarPort carPort;
    private final RentalPort rentalPort;

    @Override
    public Car getCar(Long carId) {
        return carPort.getCar(carId);
    }

    @Override
    public List<CarRentalInfoResponse> getAllCars(PageDefinition pageDefinition) {
        return carPort.getAllCars(pageDefinition)
                .stream()
                .map(car -> {
                    System.out.println(car.getId());
                    System.out.println(car.isRented());
                    Customer customer = car.isRented() ? rentalPort.findRentalByCarId(car.getId()).getCustomer() : null;
                    return new CarRentalInfoResponse(car, customer != null ? customer.getName() : null);
                }).toList();
    }

    @Override
    public Car createCar(Car car) {
        return carPort.createCar(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carPort.updateCar(car, false);
    }

    @Override
    public void deleteCar(Long carId) {
        var isRenting = rentalPort.findRentalByCarId(carId) != null;

        if (isRenting) {
            throw new CarCurrentlyRentedException(carId);
        }
        carPort.deleteCar(carId);
    }

    @Override
    public long getTotalKilometersRented(Long carId) {
        return carPort.getTotalKilometersDriven(carId);
    }
}
