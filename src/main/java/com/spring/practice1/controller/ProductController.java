package com.spring.practice1.controller;

import com.spring.practice1.data.dto.ProductDTO;
import com.spring.practice1.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product-api")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product/{productId}")
    public ProductDTO getProduct(@PathVariable String productId) {

        long startTime = System.currentTimeMillis();
        LOGGER.info("[ProductController] perform {} of KSY API.", "getProduct");

        ProductDTO productDTO = productService.getProduct(productId);

        LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, Response Time = {}ms", productDTO.getProductId(),
                productDTO.getProductName(), productDTO.getProductPrice(), productDTO.getProductStock(), (System.currentTimeMillis() - startTime));

        return productService.getProduct(productId);
    }

    @PostMapping("/product")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {

        String productId = productDTO.getProductId();
        String productName = productDTO.getProductName();
        int productPrice = productDTO.getProductPrice();
        int productStock = productDTO.getProductStock();

        ProductDTO response = productService
                .saveProduct(productId, productName, productPrice, productStock);

        LOGGER.info(
                "[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, productStock: {}",
                response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock()
        );

        return productService.saveProduct(productId, productName, productPrice, productStock);
    }


    @DeleteMapping("/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId) {return null;}

}
