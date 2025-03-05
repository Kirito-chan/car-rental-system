package com.carrental.system.application.port.in;

public interface GetTotalDistanceRentedForCarUseCase {
    long getTotalKilometersRented(Long carId);
}
