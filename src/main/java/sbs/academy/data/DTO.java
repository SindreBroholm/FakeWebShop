package sbs.academy.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class DTO {
    private UserOrder userOrder;
    private Products products;

    public DTO() {
    }

    public DTO(UserOrder userOrder, Products products) {
        this.userOrder = userOrder;
        this.products = products;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}