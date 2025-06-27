package org.example.ex1.mapper;

import org.example.ex1.dto.request.ProductCreationRequest;
import org.example.ex1.dto.response.ProductResponse;
import org.example.ex1.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Mapping {
    Product toProduct(ProductCreationRequest request);
    ProductResponse toProductResponse(Product product);
}
