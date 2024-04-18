package com.example.productorderapp;

import com.example.productorderapp.business.Product;
import com.example.productorderapp.business.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductReaderTest {
    private ProductReader productReader;
    private StubProductRepository productRepository = new StubProductRepository();

    @BeforeEach
    void setUp() {
        productReader = new ProductReader(productRepository);
    }

    private static class StubProductRepository implements ProductRepository {
        public Product productToReturn;

        @Override
        public Product addProduct(Product product) {
            return null;
        }

        @Override
        public Product getProduct(long productId) {
            return productToReturn;
        }
    }

    @Test
    void product_read_success() {
        //given
        final long productId = 1L;
        Product product = new Product("Test Product", 5000);
        productRepository.productToReturn = product;

        //when
        final GetProductResponse result = productReader.getProduct(productId);

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(productId, result.id);
        Assertions.assertEquals("Test Product", result.name);
        Assertions.assertEquals(5000, result.price);
    }

    private class ProductReader {
        private ProductRepository productRepository;

        public ProductReader(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public GetProductResponse getProduct(long productId) {
            Product product = productRepository.getProduct(productId);
                GetProductResponse response = new GetProductResponse();
                response.id = productId;
                response.name = product.getName();
                response.price = product.getPrice();
                return response;
        }
    }

    private class GetProductResponse {
        Long id;
        String name;
        int price;
    }
}
