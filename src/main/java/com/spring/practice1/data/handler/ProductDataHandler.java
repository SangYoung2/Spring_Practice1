package com.spring.practice1.data.handler;

import com.spring.practice1.data.entity.ProductEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public interface ProductDataHandler {
    ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);

    ProductEntity getProductEntity(String productId);
}
