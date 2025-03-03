package com.carrental.system.application.port.in;

import com.carrental.system.application.domain.model.Rental;

import java.util.List;

public interface RentalUseCase {
    List<Rental> findRentalsByCustomerId(Long id);

    Long getTotalRentals();

    Rental startRental(Rental rental);

    Long stopRental(Long carId, Long kilometersDriven);
}
