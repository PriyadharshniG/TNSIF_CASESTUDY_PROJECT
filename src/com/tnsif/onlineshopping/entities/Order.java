package com.tnsif.onlineshopping.entities;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity. Contains its own auto-incrementing ID counter,
 * reference to Customer, list of ProductQuantityPair and a status.
 */
public class Order {
    private static int counter = 1;

    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status; // e.g., Pending, Completed, Delivered, Cancelled

    public Order(Customer customer) {
        this.orderId = counter++;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ProductQuantityPair> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Set status to one of allowed values.
     */
    public void setStatus(String status) {
        if (status == null) return;
        String s = status.trim();
        if (s.equalsIgnoreCase("Pending")
                || s.equalsIgnoreCase("Completed")
                || s.equalsIgnoreCase("Delivered")
                || s.equalsIgnoreCase("Cancelled")) {
            this.status = capitalize(s);
        } else {
            // accept arbitrary status but normalize capitalization
            this.status = capitalize(s);
        }
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    /**
     * Add product and quantity to the order.
     */
    public void addProduct(Product product, int qty) {
        if (product == null || qty <= 0) return;
        products.add(new ProductQuantityPair(product, qty));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId)
          .append(", Customer: ").append(customer != null ? customer.getUsername() : "null")
          .append(", Status: ").append(status).append("\n");
        for (ProductQuantityPair pq : products) {
            sb.append(pq.toString()).append("\n");
        }
        return sb.toString();
    }
}