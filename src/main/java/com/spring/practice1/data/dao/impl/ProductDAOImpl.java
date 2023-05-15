package com.spring.practice1.data.dao.impl;

import com.spring.practice1.data.dao.ProductDAO;
import com.spring.practice1.data.entity.ProductEntity;
import com.spring.practice1.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {

    ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity); // 데이터 베이스에 저장을 시켜줌
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getReferenceById(productId); //ProductId를 넘겨주고 productEntity를 받아옴
        return productEntity;
    }
}
