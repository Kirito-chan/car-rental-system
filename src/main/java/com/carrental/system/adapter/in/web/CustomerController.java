package com.carrental.system.adapter.in.web;

import com.carrental.system.adapter.out.persistence.PageDefinition;
import com.carrental.system.application.domain.model.Customer;
import com.carrental.system.application.port.in.CustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerUseCase.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers(@RequestParam(defaultValue = "1", required = false) int pageNumber,
                                          @RequestParam(defaultValue = "" + Integer.MAX_VALUE, required = false) int pageSize) {
        var pageDefinition = new PageDefinition(pageNumber, pageSize);
        return customerUseCase.getAllCustomers(pageDefinition);
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerUseCase.getCustomer(id);
    }

    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerUseCase.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerUseCase.deleteCustomer(id);
    }
}
