package com.spring.practice1.data.dto;

import com.spring.practice1.data.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {

    @NotNull // api요청시 Null값인지 검사
//    @Size(min = 8, max = 8)
    private String productId;
    @NotNull
    private String productName;
    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)
    private int productPrice;
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
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
