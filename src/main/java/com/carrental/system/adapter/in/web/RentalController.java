package com.carrental.system.adapter.in.web;

import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.RentalUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
@AllArgsConstructor
class RentalController {

    private final RentalUseCase rentalUseCase;

    @PostMapping
    public Rental startRental(@RequestBody Rental rental) {
        return rentalUseCase.startRental(rental);
    }

    @DeleteMapping("/{carId}")
    public Long stopRental(@PathVariable Long carId, @RequestParam Long kilometersDriven) {
        return rentalUseCase.stopRental(carId, kilometersDriven);
    }

    @GetMapping
    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalUseCase.findRentalsByCustomerId(customerId);
    }

    @GetMapping("/total")
    public Long getTotalRentals() {
        return rentalUseCase.getTotalRentals();
    }
}
