package org.example.ex1.Filter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilter {

    String sortField;
    String sortDirection;

    @NotNull
    @Min(value = 0)
    Integer pageNo;

    @NotNull
    @Min(value = 10)
    Integer pageSize;
}
