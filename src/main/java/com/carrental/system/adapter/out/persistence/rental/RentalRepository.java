package com.carrental.system.adapter.out.persistence.rental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RentalRepository extends JpaRepository<RentalJpaEntity, Long> {
    RentalJpaEntity findByCarId(Long id);

    List<RentalJpaEntity> findByCustomerId(Long id);
}
