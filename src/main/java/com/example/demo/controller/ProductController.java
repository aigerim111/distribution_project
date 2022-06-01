package com.example.demo.controller;

import com.example.demo.entities.DictProduct;
import com.example.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/{dictProductTypeId}")
    public ResponseEntity<DictProduct> createProduct(
            @RequestBody DictProduct product,
            @PathVariable("dictProductTypeId") String productTypeId) throws IOException {
        return new ResponseEntity<>(
                productService.createProduct(product,
                        Long.parseLong(productTypeId)), HttpStatus.OK);
    }
}
