package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.model.entity.Customer;
import com.enigmacamp.shopify.service.CustomerService;
import com.enigmacamp.shopify.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;

    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String query) {
        return customerService.searchCustomers(query);
    }
}
