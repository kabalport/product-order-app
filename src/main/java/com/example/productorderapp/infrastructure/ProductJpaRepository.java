package com.example.productorderapp.infrastructure;

import com.example.productorderapp.business.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductJpaRepository {
    private Map<Long, Product> persistence = new HashMap<>();

    private Long sequence = 0L;

    public void addProduct(Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }
}
