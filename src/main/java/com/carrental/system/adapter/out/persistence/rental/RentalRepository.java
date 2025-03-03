package com.carrental.system.adapter.out.persistence.rental;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RentalRepository extends JpaRepository<RentalJpaEntity, Long> {
    List<RentalJpaEntity> findByCustomerId(Long id);

    void deleteByCarId(Long id);
}
