package com.carrental.system.adapter.out.persistence.rental;

import com.carrental.system.application.domain.model.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class RentalMapper {

    Rental mapToDomainEntity(RentalJpaEntity rentalJpaEntity) {
        return new Rental(
                rentalJpaEntity.getId(),
                rentalJpaEntity.getCustomerId(),
                rentalJpaEntity.getCarId(),
                rentalJpaEntity.getKilometersDriven());
    }

    RentalJpaEntity mapToJpaEntity(Rental rental) {
        return new RentalJpaEntity(
                rental.getId() == null ? null : rental.getId(),
                rental.getCustomerId(),
                rental.getCarId(),
                rental.getKilometersDriven());
    }
}
