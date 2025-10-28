package com.tnsif.onlineshopping.services;

import java.util.ArrayList;
import java.util.List;
import com.tnsif.onlineshopping.entities.Product;
import java.util.Collections;

/**
 * ProductService manages product catalog in memory.
 */
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
    }

    public void addProduct(Product product) {
        if (product == null) return;
        // Prevent duplicate productId
        if (findById(product.getProductId()) != null) {
            // update existing product instead of adding duplicate
            Product existing = findById(product.getProductId());
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setStockQuantity(product.getStockQuantity());
        } else {
            products.add(product);
        }
    }

    public boolean removeProduct(int productId) {
        return products.removeIf(p -> p.getProductId() == productId);
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) return p;
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }
}