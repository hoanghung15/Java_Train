package org.example.ex4.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.dto.request.CategoryRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.entity.Category;
import org.example.ex4.mapper.Mapping;
import org.example.ex4.repository.CategoryRepo;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class CategoryService {
    CategoryRepo categoryRepo;
    Mapping mapping;
    public ApiResponse addCategory(CategoryRequest request) {
        Category savedCategory = mapping.toCategory(request);
        categoryRepo.save(savedCategory);
        return ApiResponse.builder()
                .code(200)
                .message("Category added successfully")
                .result(savedCategory)
                .build();
    }
}
