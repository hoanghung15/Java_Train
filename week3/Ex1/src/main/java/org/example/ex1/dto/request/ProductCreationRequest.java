package org.example.ex1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex1.Enum.Category;
import org.example.ex1.Util.PriceValid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {

    @NotNull(message = "name not null")
    String name;

//    @Positive(message = "price must be greater than 0")
    @PriceValid(message = "price must be greater than 0")
    double price;

    Category category;
}
