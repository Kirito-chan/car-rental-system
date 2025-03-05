package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.in.web.model.StartRentalRequest;
import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.GetActiveRentalsUseCase;
import com.carrental.system.application.port.in.StartRentalUseCase;
import com.carrental.system.application.port.in.StopRentalUseCase;
import com.carrental.system.application.port.in.command.StartRentalCommand;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rental")
@AllArgsConstructor
class RentalController {

    private final StartRentalUseCase startRentalUseCase;
    private final StopRentalUseCase stopRentalUseCase;
    private final GetActiveRentalsUseCase getActiveRentalsUseCase;

    @PostMapping
    public Rental startRental(@RequestBody StartRentalRequest rental) {
        var startRentalCommand = new StartRentalCommand(rental.carId(), rental.customerId());
        return startRentalUseCase.startRental(startRentalCommand);
    }

    @DeleteMapping("/{carId}")
    public long stopRental(@PathVariable Long carId, @RequestParam int kilometersDriven) {
        return stopRentalUseCase.stopRental(carId, kilometersDriven);
    }

    @GetMapping("/total")
    public long getTotalRentals() {
        return getActiveRentalsUseCase.getTotalRentedCars();
    }
}
