package org.example.ex1.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex1.Util.MetaData;
import org.example.ex1.entity.Product;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class ListProductResponse {
    MetaData meta;
    List<ProductResponse> products;
}
