package com.carrental.system.application.port.out;

import com.carrental.system.application.domain.model.Rental;

import java.util.List;

public interface RentalPort {
    List<Rental> findRentalsByCustomerId(Long id);

    Rental createRental(Rental rental);

    void deleteRental(Rental rental);
}
