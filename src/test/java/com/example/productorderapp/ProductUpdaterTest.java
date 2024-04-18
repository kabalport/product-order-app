package com.example.productorderapp;

import com.example.productorderapp.business.Product;
import com.example.productorderapp.business.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductUpdaterTest {

    private ProductUpdater productUpdater;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        productUpdater = new ProductUpdater(productRepository);

    }

    @DisplayName("상품정보를 변경합니다.")
    @Test
    void update_product_success() {
        final Long productId = 1L;
        String name = "상품수정";
        int price = 2000;
        final UpdateProductRequest request = new UpdateProductRequest(name, price);

        final Product product = new Product("상품명",1000);
        productUpdater.updateProduct(productId, request);

        Assertions.assertEquals(product.getName(), name);
        Assertions.assertEquals(product.getPrice(),price);

    }

    private class UpdateProductRequest {
        private final String name;
        private final int price;

        public String getName() {
            return name;
        }
        public int getPrice() {
            return price;
        }
        public UpdateProductRequest(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    private class ProductUpdater {
        private Long productId;
        private UpdateProductRequest request;
        private ProductRepository productRepository;

        public ProductUpdater(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public void updateProduct(Long productId, UpdateProductRequest request) {
            this.productId = productId;
            this.request = request;
        }
    }
}
