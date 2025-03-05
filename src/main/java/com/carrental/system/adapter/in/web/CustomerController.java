package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.in.web.model.CustomerRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.port.in.CustomerCrudUseCases.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerUseCase getCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return createCustomerUseCase.createCustomer(customer);
    }

    @GetMapping
    public List<CustomerRentalInfoResponse> getAllCustomers(
            @RequestParam(defaultValue = "1", required = false) int pageNumber,
            @RequestParam(defaultValue = "" + Integer.MAX_VALUE, required = false) int pageSize) {
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        return getAllCustomersUseCase.getAllCustomers(pageDefinition);

    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return getCustomerUseCase.getCustomer(id);
    }

    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer customer) {
        return updateCustomerUseCase.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        deleteCustomerUseCase.deleteCustomer(id);
    }
}
