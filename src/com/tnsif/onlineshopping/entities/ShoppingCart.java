package com.tnsif.onlineshopping.entities;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ShoppingCart holds map of Product -> quantity.
 * Owned by a Customer.
 */
public class ShoppingCart {
    private Customer owner;
    private Map<Product, Integer> items;

    public ShoppingCart(Customer owner) {
        this.owner = owner;
        this.items = new HashMap<>();
    }

    public Customer getOwner() {
        return owner;
    }

    /**
     * Returns unmodifiable view of items.
     */
    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * Add an item to cart. Increases quantity if already present.
     */
    public void addItem(Product product, int qty) {
        if (product == null || qty <= 0) return;
        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    /**
     * Remove product from cart completely.
     */
    public void removeItem(Product product) {
        if (product == null) return;
        items.remove(product);
    }

    /**
     * Clear cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Get total item count (sum of quantities).
     */
    public int totalItems() {
        int total = 0;
        for (int q : items.values()) total += q;
        return total;
    }
}