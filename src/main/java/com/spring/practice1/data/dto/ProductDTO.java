package com.spring.practice1.data.dto;

import com.spring.practice1.data.entity.ProductEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {

    private String productId;
    private String productName;
    private int productPrice;
    private int productStock;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .productID(productId)
                .productName(productName)
                .ProductPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
