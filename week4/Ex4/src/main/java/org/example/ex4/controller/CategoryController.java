package org.example.ex4.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.dto.request.CategoryRequest;
import org.example.ex4.dto.response.ApiResponse;
import org.example.ex4.entity.Category;
import org.example.ex4.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "category-controller")
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ApiResponse addCategory(@RequestBody CategoryRequest request){
        return categoryService.addCategory(request);
    }
}
