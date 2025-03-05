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
class RentalJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_id_seq")
    @SequenceGenerator(name = "rental_id_seq", sequenceName = "rental_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rental_customer"))
    private CustomerJpaEntity customer;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rental_car"))
    private CarJpaEntity car;


}
