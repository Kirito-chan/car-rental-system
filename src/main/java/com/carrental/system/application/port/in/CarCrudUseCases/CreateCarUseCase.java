package com.carrental.system.application.port.in.CarCrudUseCases;

import com.carrental.system.application.domain.model.Car;

public interface CreateCarUseCase {
    Car createCar(Car car);
}
