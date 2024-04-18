package com.example.productorderapp;

import com.example.productorderapp.api.usecase.AddProductRequest;
import com.example.productorderapp.business.*;
import com.example.productorderapp.infrastructure.ProductCoreRepository;
import com.example.productorderapp.infrastructure.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static com.example.productorderapp.ProductFixture.getAddProductRequestFixture;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductCreatorTest {
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
    @Test
    void create_product_success() {
        //given
        final AddProductRequest request = getAddProductRequestFixture();
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        // when
        productCreator.addProduct(request);
        // then
        verify(productJpaRepository).addProduct(productCaptor.capture());
        Product capturedProduct = productCaptor.getValue();
        assertEquals(request.getName(), capturedProduct.getName());
        assertEquals(request.getPrice(), capturedProduct.getPrice());
    }

    @Test
    void create_product_failure() {
        final AddProductRequest request = getAddProductRequestFixture();
        Mockito.doThrow(new ProductException("데이터베이스 에러")).when(productJpaRepository).addProduct(any(Product.class));
        assertThrows(ProductException.class, () -> productCreator.addProduct(request),
                "상품추가에 실패했습니다.");
    }
}
