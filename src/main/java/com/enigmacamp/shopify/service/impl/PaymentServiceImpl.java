package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.PaymentRequest;
import com.enigmacamp.shopify.model.dto.response.PaymentResponse;
import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.enigmacamp.shopify.constant.APIUrl.BASE_URL;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<PaymentRequest> entity =
                new HttpEntity<>(paymentRequest, headers);

        return restTemplate.postForObject(
                BASE_URL + "/payments",
                entity,
                PaymentResponse.class
        );

    }

    @Override
    public List<Product> searchProducts(String query, int page) {
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL + "/products/search")
                .queryParam("q", query)
                .queryParam("page", page)
                .build()
                .toString();
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }

    @Override
    public void updateProduct(String id, Product product) {
        restTemplate.put(
                BASE_URL + "/products/{id}",
                product,
                id
        );

    }

    @Override
    public void deleteProduct(String id) {
        restTemplate.delete(
                BASE_URL + "/products/{id}",
                id
        );

    }
}
