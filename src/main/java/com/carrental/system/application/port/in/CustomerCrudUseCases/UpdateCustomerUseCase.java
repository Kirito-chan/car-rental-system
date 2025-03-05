package com.carrental.system.application.port.in.CustomerCrudUseCases;

import com.carrental.system.application.domain.model.Customer;

public interface UpdateCustomerUseCase {
    Customer updateCustomer(Customer customer);
}
