package com.example.productorderapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductCreatorTest {

    private ProductCreator productCreator;

    private ProductRepository productRepository;

    private ProductJpaRepository productJpaRepository;

    @BeforeEach
    void setUp() {
        // setup
        productJpaRepository = mock(ProductJpaRepository.class);
        productRepository = new ProductCoreRepository(productJpaRepository);
        productCreator = new ProductCreator(productRepository);
    }



    private AddProductRequest getAddProductRequestFixture() {
        final String name = "노트북";
        final int price = 10000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        return new AddProductRequest(name, price, discountPolicy);
    }


    @Test
    void create_product_success() {
        final AddProductRequest request = getAddProductRequestFixture();

        // when
        productCreator.addProduct(request);

        // then

        verify(productJpaRepository).addProduct(any(Product.class));

    }



    private class AddProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy policy;

        public AddProductRequest(String name, int price, DiscountPolicy policy) {

            this.name = name;
            this.price = price;
            this.policy = policy;
        }
    }

    private enum DiscountPolicy {
        None
    }

    private class ProductCreator {


        private final ProductRepository productRepository;

        private ProductCreator(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public void addProduct(AddProductRequest request) {
            final Product product = new Product(request.name, request.price, request.policy);
            productRepository.addProduct(product);
        }
    }

    private class ProductException extends RuntimeException {
        public ProductException(String message) {
            super(message);
        }
    }

    private class Product {
        private Long id;
        private final String name;
        private final int price;
        private final DiscountPolicy policy;

        public Long getId() {
            return id;
        }

        public Product(String name, int price, DiscountPolicy policy) {
            this.name = name;
            this.price = price;
            this.policy = policy;
        }


        public void assignId(Long id) {
            this.id = id;
        }
    }

    private interface ProductRepository {
        void addProduct(Product product);
    }

    private class ProductCoreRepository implements ProductRepository {
        private final ProductJpaRepository productJpaRepository;

        private ProductCoreRepository(ProductJpaRepository productJpaRepository) {
            this.productJpaRepository = productJpaRepository;
        }

        @Override
        public void addProduct(Product product) {
            productJpaRepository.addProduct(product);
        }
    }

    private class ProductJpaRepository {
        private Map<Long, Product> persistence = new HashMap<>();

        private Long sequence = 0L;

        public void addProduct(Product product) {
            product.assignId(++sequence);
            persistence.put(product.getId(),product);
        }
    }
}
