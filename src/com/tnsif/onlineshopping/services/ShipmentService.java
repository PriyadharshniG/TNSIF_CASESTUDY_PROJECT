package com.tnsif.onlineshopping.services;

import com.tnsif.onlineshopping.entities.Shipment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShipmentService {
    private List<Shipment> shipments = new ArrayList<>();

    public void addShipment(Shipment shipment) {
        if (shipment != null) {
            shipments.add(shipment);
        }
    }

    public List<Shipment> getAllShipments() {
        return Collections.unmodifiableList(shipments);
    }

    public Shipment findById(int id) {
        for (Shipment s : shipments) {
            if (s.getShipmentId() == id)
                return s;
        }
        return null;
    }
}