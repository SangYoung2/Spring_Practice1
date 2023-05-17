package com.spring.practice1.service;

import com.spring.practice1.data.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);

    ProductDTO getProduct(String productId);
}
