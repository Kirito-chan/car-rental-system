package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.in.web.model.CarRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.in.CarCrudUseCases.*;
import com.carrental.system.application.port.in.GetTotalDistanceRentedForCarUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@AllArgsConstructor
class CarController {

    private final GetTotalDistanceRentedForCarUseCase getTotalDistanceRentedForCarUseCase;
    private final CreateCarUseCase createCarUseCase;
    private final GetCarUseCase getCarUseCase;
    private final GetAllCarsUseCase getAllCarsUseCase;
    private final UpdateCarUseCase updateCarUseCase;
    private final DeleteCarUseCase deleteCarUseCase;


    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return createCarUseCase.createCar(car);
    }

    @GetMapping
    public List<CarRentalInfoResponse> getAllCars(@RequestParam(defaultValue = "1", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "" + Integer.MAX_VALUE, required = false) int pageSize) {
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        return getAllCarsUseCase.getAllCars(pageDefinition);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return getCarUseCase.getCar(id);
    }

    @GetMapping("/{id}/kilometers-driven")
    public long getTotalKilometersDriven(@PathVariable Long id) {
        return getTotalDistanceRentedForCarUseCase.getTotalKilometersRented(id);
    }

    @PutMapping()
    public Car updateCar(@RequestBody Car car) {
        return updateCarUseCase.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        deleteCarUseCase.deleteCar(id);
    }
}
