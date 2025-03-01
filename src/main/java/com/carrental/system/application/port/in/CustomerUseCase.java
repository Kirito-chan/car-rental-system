package com.carrental.system.application.port.in;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Customer;

import java.util.List;

public interface CustomerUseCase {
    Customer getCustomer(Long id);

    List<Customer> getAllCustomers(PageDefinition pageDefinition);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);
}
