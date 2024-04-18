package com.example.productorderapp.infrastructure;

import com.example.productorderapp.business.Product;
import com.example.productorderapp.business.ProductRepository;

public class ProductCoreRepository implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    public ProductCoreRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product addProduct(Product product) {
        productJpaRepository.addProduct(product);
        return product;
    }

    @Override
    public Product getProduct(long productId) {
        Product result = productJpaRepository.getProduct(productId);
        return result;
    }
}
