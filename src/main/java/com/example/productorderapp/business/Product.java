package com.example.productorderapp.business;


public class Product {
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

    public DiscountPolicy getPolicy() {
        return policy;
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
