package com.carrental.system.application.domain.model;

public record Car(Long id, String make, String model, Integer year, boolean isRented, Long totalKilometersDriven) {
}
