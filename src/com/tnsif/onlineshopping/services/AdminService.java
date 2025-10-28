package com.tnsif.onlineshopping.services;
import java.util.ArrayList;
import java.util.List;
import com.tnsif.onlineshopping.entities.Admin;
import java.util.Collections;



/**
 * AdminService stores Admin users.
 */
public class AdminService {
    private List<Admin> admins = new ArrayList<>();

    public void addAdmin(Admin admin) {
        if (admin == null) return;
        // avoid duplicate admin id
        for (Admin a : admins) {
            if (a.getUserId() == admin.getUserId()) return;
        }
        admins.add(admin);
    }

    public List<Admin> getAdmins() {
        return Collections.unmodifiableList(admins);
    }

    public Admin findById(int id) {
        for (Admin a : admins) {
            if (a.getUserId() == id) return a;
        }
        return null;
    }
}