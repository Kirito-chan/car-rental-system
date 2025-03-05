package com.carrental.system.application.port.in;

public interface StopRentalUseCase {
    long stopRental(Long carId, int kilometersDriven);
}
