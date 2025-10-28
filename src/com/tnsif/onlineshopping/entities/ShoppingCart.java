package com.tnsif.onlineshopping.entities;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    // Getter
    public Map<Product, Integer> getItems() {
        return items;
    }

    // Add an item to the cart
    public void addItem(Product product, int quantity) {
        // If product already exists, increase quantity
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    // Remove an item from the cart
    public void removeItem(Product product) {
        items.remove(product);
    }

    @Override
    public String toString() {
        return "ShoppingCart [items=" + items + "]";
    }
}