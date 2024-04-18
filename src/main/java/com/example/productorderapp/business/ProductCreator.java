package com.example.productorderapp.business;

import com.example.productorderapp.api.usecase.AddProductRequest;

public class ProductCreator {

    private final ProductRepository productRepository;

    public ProductCreator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(AddProductRequest request) {
        final Product product = new Product(request.getName(), request.getPrice(), request.getPolicy());
        productRepository.addProduct(product);
    }
}
