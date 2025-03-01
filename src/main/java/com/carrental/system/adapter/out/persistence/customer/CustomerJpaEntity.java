package com.carrental.system.adapter.out.persistence.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
class CustomerJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

}


