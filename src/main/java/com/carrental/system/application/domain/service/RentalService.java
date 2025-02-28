package com.carrental.system.application.domain.service;

import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.RentalUseCase;
import com.carrental.system.application.port.out.CarPort;
import com.carrental.system.application.port.out.RentalPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RentalService implements RentalUseCase {

    private final RentalPort rentalPort;
    private final CarPort carPort;

    @Override
    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalPort.findRentalsByCustomerId(customerId);
    }

    @Override
    public Rental startRental(Rental rental) {
        var car = carPort.getCar(rental.getCarId());
        car.setRented(true);
        carPort.updateCar(car);

        return rentalPort.createRental(rental);
    }

    @Override
    public void stopRental(Rental rental) {
        var car = carPort.getCar(rental.getCarId());
        car.setRented(false);
        car.setTotalKilometersDriven(rental.getKilometersDriven());
        carPort.updateCar(car);

        rentalPort.deleteRental(rental);
    }

    @Override
    public Long getTotalRentals() {
        return rentalPort.getTotalRentals();
    }
}
