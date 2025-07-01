package org.example.ex4.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    Integer id;
    String name;
    Double price;
    Integer categoryId;
    String categoryName;
}
