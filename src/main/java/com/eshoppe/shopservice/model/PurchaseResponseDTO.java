package com.eshoppe.shopservice.model;

import java.util.List;

public class PurchaseResponseDTO {

    private String message;
    private List<Product> products;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
