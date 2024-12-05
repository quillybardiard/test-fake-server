package com.enigmacamp.shopify.utils.mapper;

import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Named("ProductMapper")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(ProductResponse productResponse);
    List<ProductResponse> toResponseList(List<Product> products);
}
