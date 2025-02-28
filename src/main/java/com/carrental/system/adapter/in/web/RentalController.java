package com.carrental.system.adapter.in.web;

import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.domain.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
@AllArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public Rental startRental(@RequestBody Rental rental) {
        return rentalService.startRental(rental);
    }

    @DeleteMapping
    public void stopRental(@RequestBody Rental rental) {
        rentalService.stopRental(rental);
    }

    @GetMapping
    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalService.findRentalsByCustomerId(customerId);
    }
}
