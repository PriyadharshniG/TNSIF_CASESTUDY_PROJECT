package com.tnsif.onlineshopping.entities;

/**
 * Product entity with productId, name, price, stockQuantity.
 */
public class Product {
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name != null ? name : "";
        this.price = price >= 0 ? price : 0.0;
        this.stockQuantity = Math.max(0, stockQuantity);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name != null ? name : "";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) this.stockQuantity = stockQuantity;
    }

    /**
     * Reduce stock by qty if available.
     * @param qty quantity to reduce
     * @return true if reduction succeeded, false if not enough stock or invalid qty
     */
    public boolean reduceStock(int qty) {
        if (qty <= 0) return false;
        if (qty <= stockQuantity) {
            stockQuantity -= qty;
            return true;
        }
        return false;
    }

    /**
     * Increase stock by qty.
     * @param qty quantity to increase
     */
    public void increaseStock(int qty) {
        if (qty > 0) this.stockQuantity += qty;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId +
                ", name=" + name +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity + "]";
    }

    // equals and hashCode omitted for brevity but can be added if needed for collections
}