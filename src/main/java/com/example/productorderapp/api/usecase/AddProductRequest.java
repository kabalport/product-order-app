package com.example.productorderapp.api.usecase;


import com.example.productorderapp.business.DiscountPolicy;

public class AddProductRequest {
    private final String name;
    private final int price;
    private final DiscountPolicy policy;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public DiscountPolicy getPolicy() {
        return policy;
    }

    public AddProductRequest(String name, int price, DiscountPolicy policy) {

        this.name = name;
        this.price = price;
        this.policy = policy;
    }
}
