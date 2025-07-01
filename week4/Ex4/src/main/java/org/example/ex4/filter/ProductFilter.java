package org.example.ex4.filter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class ProductFilter {
    String productName;

    String sortDirection;

    @NotNull
    @Min(0)
    int pageNo;

    @NotNull
    @Min(10)
    int pageSize;


}
