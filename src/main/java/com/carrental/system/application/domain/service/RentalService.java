package com.carrental.system.application.domain.service;

import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.GetActiveRentalsUseCase;
import com.carrental.system.application.port.in.GetCustomersRentedCarsUseCase;
import com.carrental.system.application.port.in.StartRentalUseCase;
import com.carrental.system.application.port.in.StopRentalUseCase;
import com.carrental.system.application.port.in.command.StartRentalCommand;
import com.carrental.system.application.port.out.CarPort;
import com.carrental.system.application.port.out.CustomerPort;
import com.carrental.system.application.port.out.RentalPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class RentalService implements StartRentalUseCase, StopRentalUseCase, GetActiveRentalsUseCase, GetCustomersRentedCarsUseCase {

    private final RentalPort rentalPort;
    private final CustomerPort customerPort;
    private final CarPort carPort;

    @Override
    public List<Car> getRentedCarsByCustomerId(Long customerId) {
        var rentals = rentalPort.findRentalsByCustomerId(customerId);
        if (rentals.isEmpty()) {
            return new ArrayList<>();
        }
        return rentals.stream().map((rental) -> carPort.getCar(rental.getCar().getId())).toList();
    }

    @Override
    public Rental startRental(StartRentalCommand startRentalCommand) {
        var customer = customerPort.getCustomer(startRentalCommand.customerId());

        var car = carPort.getCar(startRentalCommand.carId());
        car.setRented(true);
        carPort.updateCar(car, true);

        var rental = new Rental(null, customer, car);

        return rentalPort.createRental(rental);
    }

    @Override
    public long stopRental(Long carId, int kilometersDriven) {
        var car = carPort.getCar(carId);
        car.setRented(false);
        car.setTotalKilometersDriven(car.getTotalKilometersDriven() + kilometersDriven);
        carPort.updateCar(car, true);

        rentalPort.deleteRentalByCarId(car.getId());
        return car.getTotalKilometersDriven();
    }

    @Override
    public long getTotalRentedCars() {
        return rentalPort.getTotalRentals();
    }
}
