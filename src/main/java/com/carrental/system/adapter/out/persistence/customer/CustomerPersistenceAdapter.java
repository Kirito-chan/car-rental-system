package com.carrental.system.adapter.out.persistence.customer;

import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.port.out.CustomerPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
class CustomerPersistenceAdapter implements CustomerPort {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerMapper.mapToDomainEntity(
                customerRepository.save(
                        customerMapper.mapToJpaEntity(customer)));
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerMapper.mapToDomainEntity(customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::mapToDomainEntity).toList();
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        CustomerJpaEntity customer = customerRepository.findById(updatedCustomer.id()).orElseThrow(EntityNotFoundException::new);
        customer.setName(updatedCustomer.name());
        customer.setEmail(updatedCustomer.email());

        return customerMapper.mapToDomainEntity(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
