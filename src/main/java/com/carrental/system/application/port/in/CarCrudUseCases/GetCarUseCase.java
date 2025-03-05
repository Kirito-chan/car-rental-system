package com.carrental.system.application.port.in.CarCrudUseCases;

import com.carrental.system.application.domain.model.Car;

public interface GetCarUseCase {
    Car getCar(Long carId);
}
