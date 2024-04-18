package com.example.productorderapp.business;


public class Product {
    private Long id;
    private final String name;
    private final int price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }



    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
