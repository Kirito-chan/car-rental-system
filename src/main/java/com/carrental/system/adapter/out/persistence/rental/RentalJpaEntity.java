package com.carrental.system.adapter.out.persistence.rental;

import com.carrental.system.adapter.out.persistence.car.CarJpaEntity;
import com.carrental.system.adapter.out.persistence.customer.CustomerJpaEntity;
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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerJpaEntity customer;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false, unique = true)
    private CarJpaEntity car;

    private Long kilometersDriven;
}
