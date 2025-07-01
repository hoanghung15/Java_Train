package org.example.ex4.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.dto.ProductDTO;
import org.example.ex4.dto.request.ProductCreationRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.dto.response.MetaData;
import org.example.ex4.dto.response.ProductResponse;
import org.example.ex4.entity.Category;
import org.example.ex4.entity.Product;
import org.example.ex4.filter.ProductFilter;
import org.example.ex4.mapper.Mapping;
import org.example.ex4.repository.CategoryRepo;
import org.example.ex4.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ProductService {
    Mapping mapping;
    ProductRepository productRepository;
    CategoryRepo categoryRepository;

    public ApiResponse addProduct(ProductCreationRequest request) {
        Product product = mapping.toProduct(request);
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        product.setCategory(category);
        productRepository.save(product);
        ProductDTO productResponse = mapping.toProductDto(product);
        productResponse.setCategoryId(category.getId());
        productResponse.setCategoryName(category.getName());

        return ApiResponse.builder()
                .code(200)
                .message("Product added successfully")
                .result(productResponse)
                .build();
    }

    public ApiResponse getAllProduct(ProductFilter filter) {
        String devideName = (filter.getProductName() == null || filter.getProductName().isEmpty() ? null : filter.getProductName());
        String searchDir = (filter.getSortDirection() == null || filter.getSortDirection().isEmpty() ? "desc" : filter.getSortDirection());

        Sort sort = searchDir.equalsIgnoreCase("asc")
                ? Sort.by("name").ascending()
                : Sort.by("name").descending();

        Pageable pageable = PageRequest.of(filter.getPageNo(), filter.getPageSize(), sort);
        Page<Product> result = productRepository.findAllProductWithName(devideName, pageable);

        List<ProductDTO> productResponse = result.stream()
                .map(mapping::toProductDto)
                .collect(Collectors.toList());

        return ApiResponse.<ProductResponse>builder()
                .code(200)
                .message("Product list found")
                .result(
                        ProductResponse
                                .builder()
                                .metaData(MetaData.builder()
                                        .totalItems(result.getTotalElements())
                                        .currentPage(filter.getPageNo())
                                        .pageSize(filter.getPageSize())
                                        .totalPages(result.getTotalPages())
                                        .build())
                                .products(productResponse)
                                .build()
                )
        .build();
    }

}
