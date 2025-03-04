package com.carrental.system.application.domain.service;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.port.in.CustomerUseCase;
import com.carrental.system.application.port.out.CustomerPort;
import com.carrental.system.application.port.out.RentalPort;
import com.carrental.system.common.exception.CustomerCurrentlyRentingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerPort customerPort;
    private final RentalPort rentalPort;

    @Override
    public Customer getCustomer(Long customerId) {
        return customerPort.getCustomer(customerId);
    }

    @Override
    public List<Customer> getAllCustomers(PageDefinition pageDefinition) {
        return customerPort.getAllCustomers(pageDefinition);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerPort.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerPort.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        boolean isRenting = !rentalPort.findRentalsByCustomerId(customerId).isEmpty();

        if (isRenting) {
            throw new CustomerCurrentlyRentingException(customerId);
        }

        customerPort.deleteCustomer(customerId);
    }
}
