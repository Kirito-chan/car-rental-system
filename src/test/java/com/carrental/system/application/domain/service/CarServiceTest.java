package com.carrental.system.application.domain.service;

import com.carrental.system.adapter.in.web.model.CarRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Car;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.out.CarPort;
import com.carrental.system.application.port.out.RentalPort;
import com.carrental.system.common.exception.CarCurrentlyRentedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarPort carPort;

    @Mock
    private RentalPort rentalPort;

    private Car car;
    private Car carNotRented;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        car = new Car(1L, "Toyota", "Camry", 5, 100, true, true);
        carNotRented = new Car(1L, "Toyota", "Camry", 5, 100, true, false);
        customer = new Customer(1L, "John Doe", "johndoe@email.com");
    }

    @Test
    void testGetCar() {
        when(carPort.getCar(car.getId())).thenReturn(car);

        Car retrievedCar = carService.getCar(car.getId());

        assertNotNull(retrievedCar);
        assertEquals(car.getId(), retrievedCar.getId());
        verify(carPort, times(1)).getCar(car.getId());
    }

    @Test
    void testGetAllCars_withoutCustomer() {
        PageDefinition pageDefinition = new PageDefinition(1, 10);
        when(carPort.getAllCars(pageDefinition)).thenReturn(List.of(carNotRented));
        when(rentalPort.findRentalByCarId(carNotRented.getId())).thenReturn(null);

        List<CarRentalInfoResponse> carRentalInfo = carService.getAllCars(pageDefinition);

        assertNotNull(carRentalInfo);
        assertEquals(1, carRentalInfo.size());
        assertNull(carRentalInfo.getFirst().customerName());
        verify(carPort, times(1)).getAllCars(pageDefinition);
    }

    @Test
    void testGetAllCars_withCustomer() {
        PageDefinition pageDefinition = new PageDefinition(1, 10);
        when(carPort.getAllCars(pageDefinition)).thenReturn(List.of(car));
        when(rentalPort.findRentalByCarId(car.getId())).thenReturn(new Rental(1L, customer, car));

        List<CarRentalInfoResponse> carRentalInfo = carService.getAllCars(pageDefinition);

        assertNotNull(carRentalInfo);
        assertEquals(1, carRentalInfo.size());
        assertEquals(customer.getName(), carRentalInfo.getFirst().customerName());
        verify(carPort, times(1)).getAllCars(pageDefinition);
    }

    @Test
    void testCreateCar() {
        when(carPort.createCar(car)).thenReturn(car);

        Car createdCar = carService.createCar(car);

        assertNotNull(createdCar);
        assertEquals(car.getId(), createdCar.getId());
        verify(carPort, times(1)).createCar(car);
    }

    @Test
    void testUpdateCar() {
        when(carPort.updateCar(car, false)).thenReturn(car);

        Car updatedCar = carService.updateCar(car);

        assertNotNull(updatedCar);
        assertEquals(car.getId(), updatedCar.getId());
        verify(carPort, times(1)).updateCar(car, false);
    }

    @Test
    void testDeleteCarWhenNotRented() {
        when(rentalPort.findRentalByCarId(car.getId())).thenReturn(null);

        carService.deleteCar(car.getId());

        verify(carPort, times(1)).deleteCar(car.getId());
    }

    @Test
    void testDeleteCarWhenRented() {
        // Given
        when(rentalPort.findRentalByCarId(car.getId())).thenReturn(new Rental(2L, customer, car));

        // When & Then
        CarCurrentlyRentedException exception = assertThrows(CarCurrentlyRentedException.class, () -> {
            carService.deleteCar(car.getId());
        });
        verify(carPort, times(0)).deleteCar(car.getId());
    }

    @Test
    void testGetTotalKilometersRented() {
        long totalKilometers = 1000L;
        when(carPort.getTotalKilometersDriven(car.getId())).thenReturn(totalKilometers);

        long totalDistance = carService.getTotalKilometersRented(car.getId());

        assertEquals(totalKilometers, totalDistance);
        verify(carPort, times(1)).getTotalKilometersDriven(car.getId());
    }
}
