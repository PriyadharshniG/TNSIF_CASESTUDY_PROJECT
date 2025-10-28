package com.tnsif.onlineshopping.services;
import java.util.ArrayList;
import java.util.List;
import com.tnsif.onlineshopping.entities.Order;
import com.tnsif.onlineshopping.entities.Product;
import com.tnsif.onlineshopping.entities.ProductQuantityPair;

public class OrderService {

    private List<Order> orderList;

    public OrderService() {
        orderList = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orderList.add(order);
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = getOrder(orderId);
        if (order == null) {
            System.out.println("Invalid Order");
            return;
        }

        if ("Completed".equalsIgnoreCase(status) && "Pending".equalsIgnoreCase(order.getStatus())) {
            for (ProductQuantityPair pair : order.getProducts()) {
                Product product = pair.getProduct();
                int quantity = pair.getQuantity();
                if (product.getStockQuantity() >= quantity) {
                    product.setStockQuantity(product.getStockQuantity() - quantity);
                } else {
                    System.out.println("Insufficient stock for product: " + product.getName());
                    return;
                }
            }
        } else if ("Cancelled".equalsIgnoreCase(status)) {
            if ("Completed".equalsIgnoreCase(order.getStatus()) || "Pending".equalsIgnoreCase(order.getStatus())) {
                for (ProductQuantityPair pair : order.getProducts()) {
                    Product product = pair.getProduct();
                    int quantity = pair.getQuantity();
                    product.setStockQuantity(product.getStockQuantity() + quantity);
                }
            }
        } else if ("Delivered".equalsIgnoreCase(status) && "Completed".equalsIgnoreCase(order.getStatus())) {
            // Only update the status to Delivered, no stock adjustment needed
        }

        order.setStatus(status);
    }

    public Order getOrder(int orderId) {
        return orderList.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public List<Order> getOrders() {
        return orderList;
    }
}