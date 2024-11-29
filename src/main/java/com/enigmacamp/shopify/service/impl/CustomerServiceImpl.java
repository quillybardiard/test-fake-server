package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.response.CustomerResponse;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Customer;
import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.repository.CustomerRepository;
import com.enigmacamp.shopify.utils.specifications.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {
    private final CustomerRepository customerRepository;

    public List<CustomerResponse> searchCustomers(String query) {
        Specification<Customer> spec = CustomerSpecification.getSpecification(query);
        return customerRepository.findAll(spec).stream().map(this::convertToCustomerResponse).toList();
    }

    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}
