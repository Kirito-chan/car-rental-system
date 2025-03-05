package com.carrental.system.adapter.out.persistence.rental;

import com.carrental.system.adapter.out.persistence.car.CarJpaEntity;
import com.carrental.system.adapter.out.persistence.customer.CustomerJpaEntity;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.domain.model.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class RentalMapper {

    Rental mapToDomainEntity(RentalJpaEntity rentalJpaEntity) {
        var customer = rentalJpaEntity.getCustomer();
        var car = rentalJpaEntity.getCar();
        return new Rental(
                rentalJpaEntity.getId(),
                new Customer(customer.getId(), customer.getName(), customer.getEmail()),
                new Car(car.getId(), car.getMake(), car.getModel(), car.getTotalSeats(), car.getTotalKilometersDriven(),
                        car.isAutomaticTransmission(), car.isRented()));
    }

    RentalJpaEntity mapToJpaEntity(Rental rental) {
        var customer = rental.getCustomer();
        var car = rental.getCar();
        return new RentalJpaEntity(
                rental.getId() == null ? null : rental.getId(),
                new CustomerJpaEntity(customer.getId(), customer.getName(), customer.getEmail()),
                new CarJpaEntity(car.getId(), car.getMake(), car.getModel(), car.getTotalSeats(),
                                 car.getTotalKilometersDriven(), car.isAutomaticTransmission(), car.isRented()));
    }
}
