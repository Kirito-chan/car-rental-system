package com.carrental.system.application.port.in.CarCrudUseCases;

import com.carrental.system.adapter.in.web.model.CarRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;

import java.util.List;

public interface GetAllCarsUseCase {
    List<CarRentalInfoResponse> getAllCars(PageDefinition pageDefinition);
}
