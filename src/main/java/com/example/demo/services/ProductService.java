package com.example.demo.services;

import com.example.demo.entities.DictProduct;
import com.example.demo.entities.DictProductType;
import com.example.demo.repositories.DictProductRepo;
import com.example.demo.repositories.DictProductTypeRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService {

    private final DictProductRepo productRepo;
    private final DictProductTypeRepo productTypeRepo;

    public ProductService(DictProductRepo productRepo, DictProductTypeRepo productTypeRepo) {
        this.productRepo = productRepo;
        this.productTypeRepo = productTypeRepo;
    }

    public DictProduct createProduct(DictProduct product, Long productTypeId) throws IOException {
        DictProductType productType = productTypeRepo.findById(productTypeId).orElseThrow();
        product.setDictProductType(productType);

        return productRepo.save(product);
    }
}
