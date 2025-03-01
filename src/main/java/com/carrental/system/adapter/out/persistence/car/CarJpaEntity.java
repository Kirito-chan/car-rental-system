package com.carrental.system.adapter.out.persistence.car;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
class CarJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String make;
    private String model;
    private Integer year;
    private boolean isRented;
    private Long totalKilometersDriven;
}
