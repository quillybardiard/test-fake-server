package com.enigmacamp.shopify.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
