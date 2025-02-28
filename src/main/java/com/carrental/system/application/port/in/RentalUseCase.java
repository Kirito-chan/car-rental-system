package com.carrental.system.application.port.in;

import com.carrental.system.application.domain.model.Rental;

import java.util.List;

public interface RentalUseCase {
    List<Rental> findRentalsByCustomerId(Long id);

    Rental startRental(Rental rental);

    void stopRental(Rental rental);
}
