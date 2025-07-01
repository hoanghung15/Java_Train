package org.example.ex4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.dto.request.ProductCreationRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.entity.Product;
import org.example.ex4.filter.ProductFilter;
import org.example.ex4.service.ProductService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "product-controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(description = "Add new product", summary = "Add Product")
    public ApiResponse addProduct(@RequestBody ProductCreationRequest request) {
        return productService.addProduct(request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse getAllProducts(@ParameterObject ProductFilter filter) {
        return productService.getAllProduct(filter);
    }

}
