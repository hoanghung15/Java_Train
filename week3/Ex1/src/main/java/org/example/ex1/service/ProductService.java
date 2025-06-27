package org.example.ex1.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex1.Enum.Category;
import org.example.ex1.Filter.ProductFilter;
import org.example.ex1.Util.MetaData;
import org.example.ex1.Util.ProductNotExist;
import org.example.ex1.dto.request.PaginationRequest;
import org.example.ex1.dto.response.ListProductResponse;
import org.example.ex1.entity.Product;
import org.example.ex1.mapper.Mapping;
import org.example.ex1.dto.request.ProductCreationRequest;
import org.example.ex1.dto.response.ApiResponse;
import org.example.ex1.dto.response.ProductResponse;
import org.example.ex1.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    Mapping mapping;

    public ApiResponse<ProductResponse> addProduct(ProductCreationRequest request) {
        Product product = mapping.toProduct(request);
        product = productRepository.save(product);
        ProductResponse productResponse = mapping.toProductResponse(product);
        return ApiResponse.<ProductResponse>builder()
                .message("Product added successfully")
                .status(200)
                .result(productResponse)
                .build();
    }

    public ApiResponse<ProductResponse> getProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotExist("Product Not Exist"));
        ProductResponse productResponse = mapping.toProductResponse(product);
        return ApiResponse.<ProductResponse>builder()
                .status(200)
                .message("Product found successfully")
                .result(productResponse)
                .build();
    }

    public ApiResponse<ListProductResponse> getAllProducts(ProductFilter filter) {
        String sortField = filter.getSortField() == null || filter.getSortField().isEmpty() ? "price" : filter.getSortField();
        String sortDirection = filter.getSortDirection() == null || filter.getSortDirection().isEmpty() ? "desc" : "asc";

        Sort sort = sortDirection.equals("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(filter.getPageNo(), filter.getPageSize(), sort);

        Page<Product> result = productRepository.findAll(pageable);

        List<ProductResponse> productResponse = result.map(product -> mapping.toProductResponse(product)).stream().toList();

        MetaData metaData = MetaData.builder()
                .pageNo(filter.getPageNo())
                .pageSize(filter.getPageSize())
                .totalItems(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();

        return ApiResponse.<ListProductResponse>builder()
                .status(200)
                .message("Product found successfully")
                .result(
                        ListProductResponse.builder()
                                .meta(metaData)
                                .products(productResponse)
                                .build()
                )
                .build();
    }

    public ApiResponse<ProductResponse> updateProduct(String id, ProductCreationRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotExist("Product Not Exist"));
        product = mapping.toProduct(request);
        product = productRepository.save(product);
        ProductResponse productResponse = mapping.toProductResponse(product);
        return ApiResponse.<ProductResponse>builder()
                .status(200)
                .message("Product updated successfully")
                .result(productResponse)
                .build();
    }

    public ApiResponse<ProductResponse> updateWithPatch(String id, Map<String, Object> updates) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotExist("Product Not Exist"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    product.setName(String.valueOf(value));
                    break;
                case "price":
                    product.setPrice(Double.parseDouble(value.toString()));
                    break;
                case "category":
                    product.setCategory(Category.valueOf(value.toString()));
                    break;
            }
        });

        productRepository.save(product);

        return ApiResponse.<ProductResponse>builder()
                .status(200)
                .message("Product updated successfully")
                .result(mapping.toProductResponse(product))
                .build();
    }

    public ApiResponse deleteProduct(String id) {
        productRepository.deleteById(id);
        return  ApiResponse.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();
    }
}
