package com.example.productorderapp;

import com.example.productorderapp.business.Product;
import com.example.productorderapp.business.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductReaderTest {
    private ProductReader productReader;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productReader = new ProductReader(productRepository);
    }

    @Test
    void product_read_success() {
        //given
        final long productId = 1L;

        //when
        final GetProductResponse result = productReader.getProduct(productId);

        //then
        Assertions.assertNotNull(result);
    }

    private class ProductReader {
        private ProductRepository productRepository;

        public ProductReader(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public GetProductResponse getProduct(long productId) {

            Product resp = productRepository.getProduct(productId);
            return null;
        }
    }

    private class GetProductResponse {
        Long id;
        String name;
        int price;
    }
}
