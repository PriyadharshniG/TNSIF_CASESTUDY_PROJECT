package com.tnsif.onlineshopping.services;

import java.util.*;
import com.tnsif.onlineshopping.entities.*;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    // Add order to the system
    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    // Find an order by its ID
    public Order findById(int id) {
        for (Order o : orders) {
            if (o.getOrderId() == id) {
                return o;
            }
        }
        return null;
    }

    // Update order status (Completed / Delivered / Cancelled)
    public boolean updateOrderStatus(int id, String newStatus) {
        Order order = findById(id);
        if (order != null) {
            order.setStatus(newStatus);
            return true;
        }
        return false;
    }

    // Retrieve all orders (read-only)
    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(orders);
    }
}
