package com.carrental.system.application.port.in.CustomerCrudUseCases;

import com.carrental.system.application.domain.model.Customer;

public interface GetCustomerUseCase {
    Customer getCustomer(Long id);
}
