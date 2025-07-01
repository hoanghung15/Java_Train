package org.example.ex4.mapper;

import org.example.ex4.dto.ProductDTO;
import org.example.ex4.dto.request.CategoryRequest;
import org.example.ex4.dto.request.ProductCreationRequest;
import org.example.ex4.dto.request.UserCreationRequest;
import org.example.ex4.entity.Category;
import org.example.ex4.entity.Product;
import org.example.ex4.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Mapping {
    User toUser (UserCreationRequest request);
    Product toProduct(ProductCreationRequest request);
    Category toCategory(CategoryRequest request);
    ProductDTO toProductDto(Product product);
}
