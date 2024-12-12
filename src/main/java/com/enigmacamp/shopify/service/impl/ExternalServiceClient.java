package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.utils.exeptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ExternalServiceClient {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://my-json-server.typicode.com/";
    // GET Request
    public Product getProuct(String id) {
        return  restTemplate.getForObject(
                BASE_URL + "/products/{id}",
                Product.class,
                id
        );
    }

    // Error Handling
    public Product getProductWithErrorHandling(String id) {
        try {
            return restTemplate.getForObject(
                    BASE_URL + "/products/{id}",
                    Product.class,
                    id
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + id, null
            );
        } catch (RestClientException e) {
            throw new ServiceException(
                    "Error calling external service",
                    e
            );
        }
    }
}
