package com.carrental.system.adapter.out.persistence.car;

import org.springframework.data.jpa.repository.JpaRepository;

interface CarRepository extends JpaRepository<CarJpaEntity, Long> {
    
}
