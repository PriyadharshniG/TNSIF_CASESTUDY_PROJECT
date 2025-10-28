package com.tnsif.onlineshopping.entities;

public class Shipment {
    private static int shipmentCounter = 1;
    private int shipmentId;
    private Order order;
    private String address;
    private String status;  // Pending, Shipped, Delivered, Cancelled

    public Shipment(Order order, String address) {
        this.shipmentId = shipmentCounter++;
        this.order = order;
        this.address = address;
        this.status = "Pending";
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public Order getOrder() {
        return order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void shipOrder() {
        this.status = "Shipped";
    }

    public void deliverOrder() {
        this.status = "Delivered";
    }

    @Override
    public String toString() {
        return "Shipment [shipmentId=" + shipmentId +
               ", orderId=" + (order != null ? order.getOrderId() : "N/A") +
               ", address='" + address + '\'' +
               ", status='" + status + '\'' +
               ']';
    }
}