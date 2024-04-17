package com.example.productorderapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {
    private ProductService productService;

    @Test
    void createProduct() {

        final String name = "상품명";
        final int price = 1000;
        final CreateProductRequest request = new CreateProductRequest(name, price, DiscountPolicy.None);

        productService.addProduct(request);


        Assertions.assertEquals(new Product("상품명", 1000, DiscountPolicy.None), productService.addProduct(new CreateProductRequest("상품명", 1000, DiscountPolicy.None)));
    }

    public static class CreateProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy none;

        public CreateProductRequest(String name, int price, DiscountPolicy none) {
            this.name = name;
            this.price = price;
            this.none = none;
        }
    }

    private enum DiscountPolicy {
        None
    }

    private class ProductService {
        public Product addProduct(CreateProductRequest request) {

            return null;
        }
    }


    private class Product {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public Product(String name, int price, DiscountPolicy discountPolicy) {
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }
}
