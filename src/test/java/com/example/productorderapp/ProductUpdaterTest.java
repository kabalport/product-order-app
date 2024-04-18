package com.example.productorderapp;

import com.example.productorderapp.business.Product;
import com.example.productorderapp.business.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductUpdaterTest {

    private static ProductUpdater productUpdater;
    //    private ProductRepository productRepository;
    private StubProductRepository productRepository = new StubProductRepository();

    @BeforeEach
    void setUp() {
        productUpdater = new ProductUpdater(productRepository);
    }

    private static class StubProductRepository implements ProductRepository{
        public Product getProduct_will_return;

        @Override
        public Product addProduct(Product product) {
            return getProduct_will_return;
        }

        @Override
        public Product getProduct(long productId) {
            return null;
        }
    }

    @DisplayName("상품정보를 변경합니다.")
    @Test
    void update_product_success() {
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품수정", 2000);

        final Product product = new Product("상품수정",2000);
        productRepository.getProduct_will_return = product;


        productUpdater.updateProduct(productId, request);

        Assertions.assertEquals(product.getName(), "상품수정");
        Assertions.assertEquals(product.getPrice(),2000);

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
