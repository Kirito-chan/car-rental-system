package com.carrental.system.adapter.in.web;

import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/rented")
    public List<Car> getAllRentedCars() {
        return carService.getAllRentedCars();
    }

    @GetMapping("/{id}/kilometers-driven")
    public Long getTotalKilometersDriven(@PathVariable Long id) {
        return carService.getTotalKilometersDriven(id);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PutMapping()
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
