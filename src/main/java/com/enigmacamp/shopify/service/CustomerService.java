package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.response.CustomerResponse;
import com.enigmacamp.shopify.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> searchCustomers(String query);
    CustomerResponse create(Customer customer);
}
