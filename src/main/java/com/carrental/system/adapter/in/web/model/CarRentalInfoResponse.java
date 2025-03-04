package com.carrental.system.adapter.in.web.model;

import com.carrental.system.application.domain.model.Car;

public record CarRentalInfoResponse(
        Car car,
        String customerName    // Nullable - only present if the car is rented
) {
}

