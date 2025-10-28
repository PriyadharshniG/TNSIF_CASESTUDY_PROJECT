package com.tnsif.onlineshopping.services;

import com.tnsif.onlineshopping.entities.Payment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentService {
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        if (payment != null) {
            payments.add(payment);
        }
    }

    public List<Payment> getAllPayments() {
        return Collections.unmodifiableList(payments);
    }

    public Payment findById(int id) {
        for (Payment p : payments) {
            if (p.getPaymentId() == id) return p;
        }
        return null;
    }
}