package com.carrental.system.adapter.out.persistence.car;


import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.port.out.CarPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.carrental.system.adapter.out.persistence.PageDefinitionUtil.toPageable;

@RequiredArgsConstructor
@Component
class CarPersistenceAdapter implements CarPort {

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
    public List<Car> getAllCars(PageDefinition pageDefinition) {
        return carRepository.findAll(toPageable(pageDefinition))
                .stream()
                .map(carMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public long getTotalKilometersDriven(Long carId) {
        return getCar(carId).getTotalKilometersDriven();
    }

    @Override
    public Car updateCar(Car updatedCar, boolean updateRented) {
        CarJpaEntity car = carRepository.findById(updatedCar.getId()).orElseThrow(EntityNotFoundException::new);
        car.setMake(updatedCar.getMake());
        car.setModel(updatedCar.getModel());
        car.setTotalSeats(updatedCar.getTotalSeats());
        car.setAutomaticTransmission(updatedCar.isAutomaticTransmission());
        car.setTotalKilometersDriven(updatedCar.getTotalKilometersDriven());
        if (updateRented) {
            car.setRented(updatedCar.isRented());
        }

        return carMapper.mapToDomainEntity(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }


}
