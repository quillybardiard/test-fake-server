package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ExternalService {
    private final RestClient restClient;
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public ResponseData getData(String id) {
        return restClient.get()
                .uri(BASE_URL + "/data/" + id)
                .retrieve()
                .body(ResponseData.class);
    }

}
