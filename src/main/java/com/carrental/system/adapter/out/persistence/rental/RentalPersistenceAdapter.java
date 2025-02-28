package com.carrental.system.adapter.out.persistence.rental;


import com.carrental.system.application.domain.model.Rental;
import com.carrental.system.application.port.out.RentalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RentalPersistenceAdapter implements RentalPort {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    @Override
    public Rental createRental(Rental rental) {

        return rentalMapper.mapToDomainEntity(
                rentalRepository.save(
                        rentalMapper.mapToJpaEntity(rental)));
    }

    @Override
    public Long getTotalRentals() {
        return rentalRepository.count();
    }

    @Override
    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalRepository.findByCustomerId(customerId).stream().map(rentalMapper::mapToDomainEntity).toList();
    }

    @Override
    public void deleteRental(Rental rental) {
        rentalRepository.deleteById(rental.getId());
    }
}
