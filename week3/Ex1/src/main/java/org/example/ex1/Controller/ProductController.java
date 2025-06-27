package org.example.ex1.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex1.Filter.ProductFilter;
import org.example.ex1.dto.request.PaginationRequest;
import org.example.ex1.dto.request.ProductCreationRequest;
import org.example.ex1.dto.response.ApiResponse;
import org.example.ex1.dto.response.ListProductResponse;
import org.example.ex1.dto.response.ProductResponse;
import org.example.ex1.entity.Product;
import org.example.ex1.service.ProductService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Product-Controller")
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @Operation(description = "Create new Product", summary = "Create new Product")
    @PostMapping("")
    ApiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return productService.addProduct(request);
    }

    @Operation(description = "Get detail Product", summary = "Get detail Product")
    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @Operation(description = "Get all Products", summary = "Get all products")
    @GetMapping
    ApiResponse<ListProductResponse> getAllProducts(@ParameterObject ProductFilter filter) {
        return productService.getAllProducts(filter);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update Product", summary = "Update Product")
    ApiResponse<ProductResponse> updateProduct(
            @PathVariable String id,
            @RequestBody @Valid ProductCreationRequest request) {
        return productService.updateProduct(id, request);
    }

    @Operation(description = "Update product with Patch", summary = "update product")
    @PatchMapping("/{id}")
    public ApiResponse<ProductResponse> patchProduct(
            @PathVariable String id,
            @RequestBody Map<String,Object> updates) {
        return productService.updateWithPatch(id, updates);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete product", summary = "Delete product")
    public ApiResponse deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
