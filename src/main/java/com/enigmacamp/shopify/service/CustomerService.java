package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> searchCustomers(String query);
}
