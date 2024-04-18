package com.example.productorderapp;

import com.example.productorderapp.api.usecase.AddProductRequest;
import com.example.productorderapp.business.DiscountPolicy;

public class ProductFixture {
    public static AddProductRequest getAddProductRequestFixture() {
        final String name = "노트북";
        final int price = 10000;
        final DiscountPolicy discountPolicy = DiscountPolicy.None;
        return new AddProductRequest(name, price, discountPolicy);
    }
}
