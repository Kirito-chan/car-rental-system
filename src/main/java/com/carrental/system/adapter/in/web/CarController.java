package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.in.CarUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@AllArgsConstructor
class CarController {

    private final CarUseCase carUseCase;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carUseCase.createCar(car);
    }

    @GetMapping
    public List<Car> getAllCars(@RequestParam(defaultValue = "1", required = false) int pageNumber,
                                @RequestParam(defaultValue = "" + Integer.MAX_VALUE, required = false) int pageSize) {
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        return carUseCase.getAllCars(pageDefinition);
    }

    @GetMapping("/{id}/kilometers-driven")
    public Long getTotalKilometersDriven(@PathVariable Long id) {
        return carUseCase.getTotalKilometersDriven(id);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carUseCase.getCar(id);
    }

    @PutMapping()
    public Car updateCar(@RequestBody Car car) {
        return carUseCase.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carUseCase.deleteCar(id);
    }
}
