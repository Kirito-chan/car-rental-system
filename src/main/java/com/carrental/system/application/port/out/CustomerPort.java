package com.carrental.system.application.port.out;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Customer;

import java.util.List;

public interface CustomerPort {
    Customer getCustomer(Long customerId);

    List<Customer> getAllCustomers(PageDefinition pageDefinition);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long customerId);
}
