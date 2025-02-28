package com.carrental.system.application.domain.model;

public record Rental(Long id,
                     Customer customer,
                     Car car,
                     Long kilometersDriven) {
}
