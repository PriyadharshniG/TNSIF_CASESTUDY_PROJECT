package com.tnsif.onlineshopping.entities;

/**
 * Simple holder for product and its ordered quantity inside an Order.
 */
public class ProductQuantityPair {
    private Product product;
    private int quantity;

    public ProductQuantityPair(Product product, int quantity) {
        this.product = product;
        this.quantity = Math.max(0, quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
    }

    @Override
    public String toString() {
        return "  Product: " + (product != null ? product.getName() : "null") + ", Quantity: " + quantity;
    }
}