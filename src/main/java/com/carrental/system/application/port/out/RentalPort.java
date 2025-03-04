package com.carrental.system.application.port.out;

import com.carrental.system.application.domain.model.Rental;

import java.util.List;

public interface RentalPort {
    List<Rental> findRentalsByCustomerId(Long customerId);

    Rental findRentalByCarId(Long carId);

    Long getTotalRentals();

    Rental createRental(Rental rental);

    void deleteRentalByCarId(Long carId);
}
