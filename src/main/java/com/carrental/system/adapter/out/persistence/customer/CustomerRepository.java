package com.carrental.system.adapter.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
}
