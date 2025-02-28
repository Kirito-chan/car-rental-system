package com.carrental.system.adapter.out.persistence.car;


import com.carrental.system.application.domain.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car mapToDomainEntity(CarJpaEntity carJpaEntity) {
        return new Car(
                carJpaEntity.getId(),
                carJpaEntity.getMake(),
                carJpaEntity.getModel(),
                carJpaEntity.getYear(),
                carJpaEntity.isRented(),
                carJpaEntity.getTotalKilometersDriven());
    }

    public CarJpaEntity mapToJpaEntity(Car car) {
        return new CarJpaEntity(
                car.getId() == null ? null : car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.isRented(),
                car.getTotalKilometersDriven());
    }
}
