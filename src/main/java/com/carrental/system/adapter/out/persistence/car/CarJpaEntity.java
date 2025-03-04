package com.carrental.system.adapter.out.persistence.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_id_seq")
    @SequenceGenerator(name = "car_id_seq", sequenceName = "car_id_seq", allocationSize = 1)
    private Long id;

    private String make;
    private String model;
    private Integer totalSeats;
    private boolean isAutomaticTransmission;
    private boolean isRented;
    private Long totalKilometersDriven;
}
