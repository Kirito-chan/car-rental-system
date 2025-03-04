package com.carrental.system.application.port.in;

import com.carrental.system.adapter.in.web.model.StartRentalRequest;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.domain.model.Rental;

import java.util.List;

public interface RentalUseCase {
    List<Rental> findRentalsByCustomerId(Long customerId);

    Customer findCustomerByCarId(Long carId);

    List<Car> findRentedCarsByCustomerId(Long customerId);

    Long getTotalRentals();

    Rental startRental(StartRentalRequest rental);

    Long stopRental(Long carId, Long kilometersDriven);
}
