package com.tnsif.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;


/**
 * Customer extends User and holds address, shoppingCart and orders list.
 */
public class Customer extends User {
    private String address;
    private ShoppingCart shoppingCart;
    private List<Order> orders;

    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.address = address != null ? address : "";
        this.shoppingCart = new ShoppingCart(this);
        this.orders = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address != null ? address : "";
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (order != null) this.orders.add(order);
    }

    @Override
    public String toString() {
        return super.toString() + ", Address: " + address;
    }
}