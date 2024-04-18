package com.example.productorderapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductUpdaterTest {

    @DisplayName("상품정보를 변경합니다.")
    @Test
    void update_product_success() {
        ProductUpdateRequest request = new ProductUpdateRequest("상품명");
        productUpdater.updateProduct(request);
    }

    private class ProductUpdateRequest {
    }
}
