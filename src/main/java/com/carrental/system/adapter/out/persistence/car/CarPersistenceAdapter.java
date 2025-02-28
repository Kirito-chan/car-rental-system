package com.carrental.system.adapter.out.persistence.car;


import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.out.CarPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CarPersistenceAdapter implements CarPort {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public Car createCar(Car car) {
        return carMapper.mapToDomainEntity(
                carRepository.save(
                        carMapper.mapToJpaEntity(car)));
    }

    @Override
    public Car getCar(Long carId) {
        return carMapper.mapToDomainEntity(carRepository.findById(carId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::mapToDomainEntity).toList();
    }

    @Override
    public Long getTotalKilometersDriven(Long carId) {
        return getCar(carId).getTotalKilometersDriven();
    }

    @Override
    public Car updateCar(Car updatedCar) {
        CarJpaEntity car = carRepository.findById(updatedCar.getId()).orElseThrow(EntityNotFoundException::new);
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setRented(updatedCar.isRented());

        return carMapper.mapToDomainEntity(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }


}
