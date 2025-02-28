package com.carrental.system.adapter.out.persistence.rental;

import com.carrental.system.adapter.out.persistence.car.CarMapper;
import com.carrental.system.adapter.out.persistence.customer.CustomerMapper;
import com.carrental.system.application.domain.model.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RentalMapper {

    private final CustomerMapper customerMapper;
    private final CarMapper carMapper;

    Rental mapToDomainEntity(RentalJpaEntity rentalJpaEntity) {
        return new Rental(
                rentalJpaEntity.getId(),
                customerMapper.mapToDomainEntity(rentalJpaEntity.getCustomer()),
                carMapper.mapToDomainEntity(rentalJpaEntity.getCar()),
                rentalJpaEntity.getKilometersDriven());
    }

    RentalJpaEntity mapToJpaEntity(Rental rental) {
        return new RentalJpaEntity(
                rental.id() == null ? null : rental.id(),
                customerMapper.mapToJpaEntity(rental.customer()),
                carMapper.mapToJpaEntity(rental.car()),
                rental.kilometersDriven());
    }
}
