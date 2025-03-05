package com.carrental.system.application.port.in.CustomerCrudUseCases;

import com.carrental.system.adapter.in.web.model.CustomerRentalInfoResponse;
import com.carrental.system.adapter.out.persistence.PageDefinition;

import java.util.List;

public interface GetAllCustomersUseCase {
    List<CustomerRentalInfoResponse> getAllCustomers(PageDefinition pageDefinition);
}
