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
public class RentalJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "car_id", nullable = false, unique = true)
    private Long carId;

    private Long kilometersDriven;
}
