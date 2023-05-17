package com.spring.practice1.service.impl;

import com.spring.practice1.data.dto.ProductDTO;
import com.spring.practice1.data.entity.ProductEntity;
import com.spring.practice1.data.handler.ProductDataHandler;
import com.spring.practice1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDataHandler productDataHandler;

    @Autowired
    public ProductServiceImpl(ProductDataHandler productDataHandler) {
        this.productDataHandler = productDataHandler;
    }

    @Override
    public ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        ProductDTO productDTO = new ProductDTO(productEntity.getProductID(), productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStock());

        return productDTO;
    }

    @Override
    public ProductDTO getProduct(String productId) {
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);

        ProductDTO productDTO = new ProductDTO(productEntity.getProductID(), productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStock());

        return productDTO;
    }
}
