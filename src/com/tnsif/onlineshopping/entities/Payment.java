package com.tnsif.onlineshopping.entities;

public class Payment {
    private static int paymentCounter = 1;
    private int paymentId;
    private Order order;
    private double amount;
    private String paymentMode;  // e.g., UPI, Card, COD
    private String status;       // e.g., Pending, Successful, Failed

    // Constructor
    public Payment(Order order, double amount, String paymentMode) {
        this.paymentId = paymentCounter++;
        this.order = order;
        this.amount = amount;
        this.paymentMode = paymentMode != null ? paymentMode : "Unknown";
        this.status = "Pending";
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Simulate processing the payment
    public void processPayment() {
        if (amount > 0) {
            this.status = "Successful";
        } else {
            this.status = "Failed";
        }
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId +
               ", orderId=" + (order != null ? order.getOrderId() : "N/A") +
               ", amount=" + amount +
               ", mode=" + paymentMode +
               ", status=" + status + "]";
    }
}