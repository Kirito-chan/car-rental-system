package com.carrental.system.adapter.out.persistence.customer;

import com.carrental.system.application.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToDomainEntity(CustomerJpaEntity customerJpaEntity) {
        return new Customer(customerJpaEntity.getId(), customerJpaEntity.getName(), customerJpaEntity.getEmail());
    }

    public CustomerJpaEntity mapToJpaEntity(Customer customer) {
        return new CustomerJpaEntity(
                customer.getId() == null ? null : customer.getId(),
                customer.getName(),
                customer.getEmail());
    }
}
