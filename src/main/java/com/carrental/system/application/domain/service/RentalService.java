package com.carrental.system.application.domain.service;

import com.carrental.system.adapter.in.web.model.StartRentalRequest;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.RentalUseCase;
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
public class RentalService implements RentalUseCase {

    private final RentalPort rentalPort;
    private final CustomerPort customerPort;
    private final CarPort carPort;

    @Override
    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalPort.findRentalsByCustomerId(customerId);
    }

    @Override
    public Customer findCustomerByCarId(Long carId) {
        var rental = rentalPort.findRentalByCarId(carId);
        return customerPort.getCustomer(rental.getCustomer().getId());
    }

    @Override
    public List<Car> findRentedCarsByCustomerId(Long customerId) {
        var rentals = this.findRentalsByCustomerId(customerId);
        if (rentals.isEmpty()) {
            return new ArrayList<>();
        }
        return rentals.stream().map((rental) -> carPort.getCar(rental.getCar().getId())).toList();
    }

    @Override
    public Rental startRental(StartRentalRequest startRentalRequest) {
        var rental = mapStartRentalRequestToDomain(startRentalRequest);
        var car = rental.getCar();
        car.setRented(true);
        carPort.updateCar(car);
        rental.setCar(car);
        
        return rentalPort.createRental(rental);
    }

    @Override
    public Long stopRental(Long carId, Long kilometersDriven) {
        var car = carPort.getCar(carId);
        car.setRented(false);
        car.setTotalKilometersDriven(car.getTotalKilometersDriven() + kilometersDriven);
        carPort.updateCar(car);

        rentalPort.deleteRentalByCarId(car.getId());
        return car.getTotalKilometersDriven();
    }

    @Override
    public Long getTotalRentals() {
        return rentalPort.getTotalRentals();
    }

    private Rental mapStartRentalRequestToDomain(StartRentalRequest rental) {
        var customer = customerPort.getCustomer(rental.customerId());
        var car = carPort.getCar(rental.carId());
        return new Rental(null, customer, car);
    }
}
