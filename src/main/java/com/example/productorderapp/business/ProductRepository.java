package com.example.productorderapp.business;

public interface ProductRepository {
    Product addProduct(Product product);

    Product getProduct(long productId);
}
