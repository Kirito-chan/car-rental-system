package com.carrental.system.adapter.out.persistence.rental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface RentalRepository extends JpaRepository<RentalJpaEntity, Long> {
    List<RentalJpaEntity> findByCustomerId(Long id);

    Optional<RentalJpaEntity> findByCarId(Long carId);

    void deleteByCarId(Long id);
}
