package com.example.productorderapp.api.usecase;


public class AddProductRequest {
    private final String name;
    private final int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public AddProductRequest(String name, int price) {

        this.name = name;
        this.price = price;
    }
}
