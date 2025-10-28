package com.tnsif.onlineshopping.services;
import com.tnsif.onlineshopping.entities.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;



/**
 * CustomerService manages customers.
 */
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer c) {
        if (c == null) return;
        if (findById(c.getUserId()) != null) return; // prevent duplicate id
        customers.add(c);
    }

    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(customers);
    }
}
