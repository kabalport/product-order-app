package com.example.productorderapp;

import com.example.productorderapp.api.usecase.AddProductRequest;

public class ProductFixture {
    public static AddProductRequest getAddProductRequestFixture() {
        final String name = "노트북";
        final int price = 10000;
        return new AddProductRequest(name, price);
    }
}
