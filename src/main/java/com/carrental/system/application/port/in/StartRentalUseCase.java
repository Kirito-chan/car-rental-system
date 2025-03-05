package com.carrental.system.application.port.in;

import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.in.command.StartRentalCommand;

public interface StartRentalUseCase {
    Rental startRental(StartRentalCommand startRentalCommand);
}
