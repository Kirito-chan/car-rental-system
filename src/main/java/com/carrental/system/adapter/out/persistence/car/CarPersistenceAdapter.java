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
    public List<Car> getAllRentedCars() {
        return carRepository.findAll().stream().map(carMapper::mapToDomainEntity).toList();
    }

    @Override
    public Long getTotalKilometersDriven(Long carId) {
        return getCar(carId).totalKilometersDriven();
    }

    @Override
    public Car updateTotalKilometersDriven(Long carId, Long kilometersDriven) {
        var carJpaEntity = carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
        carJpaEntity.setTotalKilometersDriven(carJpaEntity.getTotalKilometersDriven() + kilometersDriven);

        return carMapper.mapToDomainEntity(carRepository.save(carJpaEntity));
    }

    @Override
    public Car updateCar(Car updatedCar) {
        CarJpaEntity car = carRepository.findById(updatedCar.id()).orElseThrow(EntityNotFoundException::new);
        car.setMake(updatedCar.make());
        car.setModel(updatedCar.model());
        car.setYear(updatedCar.year());
        car.setRented(updatedCar.isRented());

        return carMapper.mapToDomainEntity(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }


}
