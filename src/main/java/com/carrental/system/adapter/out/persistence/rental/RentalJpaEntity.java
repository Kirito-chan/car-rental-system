package com.carrental.system.adapter.out.persistence.rental;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rental", uniqueConstraints = @UniqueConstraint(columnNames = "car_id"))
@Data
@AllArgsConstructor
@NoArgsConstructor
class RentalJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_id_seq")
    @SequenceGenerator(name = "rental_id_seq", sequenceName = "rental_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "car_id", nullable = false, unique = true)
    private Long carId;
}
