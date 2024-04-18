package com.example.productorderapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
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
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);

        // when
        productCreator.addProduct(request);

        // then
        //test

        verify(productJpaRepository).addProduct(productCaptor.capture());  // 캡처와 검증을 동시에 수행
        Product capturedProduct = productCaptor.getValue();
        assertEquals(request.getName(), capturedProduct.getName());
        assertEquals(request.getPrice(), capturedProduct.getPrice());
    }
    //step1,2

    @Test
    void create_product_failure() {
        final AddProductRequest request = getAddProductRequestFixture();
        Mockito.doThrow(new RuntimeException("Database error")).when(productJpaRepository).addProduct(any(Product.class));

        assertThrows(RuntimeException.class, () -> productCreator.addProduct(request),
                "Expected addProduct to throw, but it did not");
    }



    private class AddProductRequest {
        private final String name;
        private final int price;
        private final DiscountPolicy policy;

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }


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

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
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
