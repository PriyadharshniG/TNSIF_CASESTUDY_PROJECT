package com.tnsif.onlineshopping.entities;

/**
 * Admin extends User. Admins can manage products and orders (service layer handles logic).
 */
public class Admin extends User {

    public Admin(int userId, String username, String email) {
        super(userId, username, email);
    }

    @Override
    public String toString() {
        return "Admin - " + super.toString();
    }
}