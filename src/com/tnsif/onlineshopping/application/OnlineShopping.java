package com.tnsif.onlineshopping.application;

import com.tnsif.onlineshopping.entities.Admin;
import com.tnsif.onlineshopping.entities.Customer;
import com.tnsif.onlineshopping.entities.Order;
import com.tnsif.onlineshopping.entities.Product;
import com.tnsif.onlineshopping.entities.Payment;
import com.tnsif.onlineshopping.entities.Shipment;

import com.tnsif.onlineshopping.services.AdminService;
import com.tnsif.onlineshopping.services.CustomerService;
import com.tnsif.onlineshopping.services.OrderService;
import com.tnsif.onlineshopping.services.ProductService;
import com.tnsif.onlineshopping.services.PaymentService;
import com.tnsif.onlineshopping.services.ShipmentService;

import java.util.Scanner;

public class OnlineShopping {

    private static final ProductService productService = new ProductService();
    private static final AdminService adminService = new AdminService();
    private static final CustomerService customerService = new CustomerService();
    private static final OrderService orderService = new OrderService();
    private static final PaymentService paymentService = new PaymentService();
    private static final ShipmentService shipmentService = new ShipmentService();

    public static void main(String[] args) {
        seedSampleData();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n========= ONLINE SHOPPING APPLICATION =========");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = readInt(sc);

            switch (mainChoice) {
                case 1:
                    adminMenu(sc);
                    break;
                case 2:
                    customerMenu(sc);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting... Thank you for using Online Shopping App!");
                    break;
                default:
                    System.out.println("Invalid option, please try again!");
            }
        }
        sc.close();
    }

    private static void seedSampleData() {
        productService.addProduct(new Product(101, "T-Shirt", 560.0, 100));
        productService.addProduct(new Product(102, "Trouser", 1400.0, 50));
        productService.addProduct(new Product(103, "Sneakers", 2500.0, 30));
    }

    private static void adminMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n========= ADMIN MENU =========");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. View Payments");
            System.out.println("9. View Shipments");
            System.out.println("10. Return to Main Menu");
            System.out.print("Choose an option: ");
            int ch = readInt(sc);

            switch (ch) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int pid = readInt(sc);
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = readDouble(sc);
                    System.out.print("Enter Stock Quantity: ");
                    int qty = readInt(sc);
                    productService.addProduct(new Product(pid, name, price, qty));
                    System.out.println("✅ Product added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int rid = readInt(sc);
                    if (productService.removeProduct(rid))
                        System.out.println("✅ Product removed successfully!");
                    else
                        System.out.println("❌ Product not found!");
                    break;

                case 3:
                    System.out.println("\nAvailable Products:");
                    for (Product p : productService.getAllProducts())
                        System.out.println(p);
                    break;

                case 4:
                    System.out.print("Enter Admin ID: ");
                    int aid = readInt(sc);
                    System.out.print("Enter Admin Username: ");
                    String aname = sc.nextLine();
                    System.out.print("Enter Admin Email: ");
                    String aemail = sc.nextLine();
                    adminService.addAdmin(new Admin(aid, aname, aemail));
                    System.out.println("✅ Admin created successfully!");
                    break;

                case 5:
                    System.out.println("\nRegistered Admins:");
                    for (Admin a : adminService.getAdmins())
                        System.out.println(a);
                    break;

                case 6:
                    System.out.print("Enter Order ID: ");
                    int oid = readInt(sc);
                    Order o = orderService.findById(oid);
                    if (o == null) {
                        System.out.println("❌ Order not found!");
                        break;
                    }
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String st = sc.nextLine();
                    o.setStatus(st);
                    System.out.println("✅ Order status updated!");
                    break;

                case 7:
                    System.out.println("\nAll Orders:");
                    for (Order order : orderService.getAllOrders())
                        System.out.println(order);
                    break;

                case 8:
                    System.out.println("\nAll Payments:");
                    for (Payment p : paymentService.getAllPayments())
                        System.out.println(p);
                    break;

                case 9:
                    System.out.println("\nAll Shipments:");
                    for (Shipment s : shipmentService.getAllShipments())
                        System.out.println(s);
                    break;

                case 10:
                    back = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid option, try again!");
            }
        }
    }

    private static void customerMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n========= CUSTOMER MENU =========");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View My Orders");
            System.out.println("5. View Products");
            System.out.println("6. View My Shipments");
            System.out.println("7. Return to Main Menu");
            System.out.print("Choose an option: ");
            int ch = readInt(sc);

            switch (ch) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = readInt(sc);
                    System.out.print("Enter Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String uemail = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String addr = sc.nextLine();
                    customerService.addCustomer(new Customer(uid, uname, uemail, addr));
                    System.out.println("✅ Customer created successfully!");
                    break;

                case 2:
                    System.out.println("\nRegistered Customers:");
                    for (Customer c : customerService.getAllCustomers())
                        System.out.println(c);
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    int cid = readInt(sc);
                    Customer cust = customerService.findById(cid);
                    if (cust == null) {
                        System.out.println("❌ Customer not found!");
                        break;
                    }

                    Order order = new Order(cust);
                    while (true) {
                        System.out.print("Enter Product ID to add (or -1 to finish): ");
                        int pid2 = readInt(sc);
                        if (pid2 == -1) break;
                        Product p = productService.findById(pid2);
                        if (p == null) {
                            System.out.println("❌ Product not found!");
                            continue;
                        }
                        System.out.print("Enter quantity: ");
                        int q = readInt(sc);
                        if (q <= 0) {
                            System.out.println("Invalid quantity!");
                            continue;
                        }
                        if (p.getStockQuantity() < q) {
                            System.out.println("Not enough stock. Available: " + p.getStockQuantity());
                            continue;
                        }
                        p.reduceStock(q);
                        order.addProduct(p, q);
                    }

                    orderService.addOrder(order);
                    cust.addOrder(order);
                    System.out.println("✅ Order placed successfully!");

                    // --- Payment Process ---
                    double totalAmount = 0.0;
                    for (var pq : order.getProducts()) {
                        totalAmount += pq.getProduct().getPrice() * pq.getQuantity();
                    }
                    System.out.print("Enter payment mode (UPI/Card/COD): ");
                    String mode = sc.nextLine();
                    Payment payment = new Payment(order, totalAmount, mode);
                    payment.processPayment();
                    paymentService.addPayment(payment);
                    System.out.println("✅ Payment processed successfully: " + payment);

                    // --- Shipment Creation ---
                    System.out.print("Enter delivery address: ");
                    String shipAddr = sc.nextLine();
                    Shipment shipment = new Shipment(order, shipAddr);
                    shipment.shipOrder();
                    shipmentService.addShipment(shipment);
                    System.out.println("🚚 Shipment created successfully: " + shipment);
                    // -----------------------

                    break;

                case 4:
                    System.out.print("Enter Customer ID: ");
                    int ccid = readInt(sc);
                    Customer customer = customerService.findById(ccid);
                    if (customer == null) {
                        System.out.println("❌ Customer not found!");
                        break;
                    }
                    System.out.println("\nMy Orders:");
                    for (Order o : customer.getOrders())
                        System.out.println(o);
                    break;

                case 5:
                    System.out.println("\nAvailable Products:");
                    for (Product p : productService.getAllProducts())
                        System.out.println(p);
                    break;

                case 6:
                    System.out.print("Enter your Customer ID: ");
                    int shipCid = readInt(sc);
                    Customer shipCustomer = customerService.findById(shipCid);
                    if (shipCustomer == null) {
                        System.out.println("❌ Customer not found!");
                        break;
                    }

                    System.out.println("\nMy Shipments:");
                    boolean found = false;
                    for (Shipment s : shipmentService.getAllShipments()) {
                        if (s.getOrder().getCustomer().getUserId() == shipCustomer.getUserId()) {
                            System.out.println(s);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No shipments found for this customer.");
                    }
                    break;

                case 7:
                    back = true;
                    System.out.println("Returning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid option, try again!");
            }
        }
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, try again: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount, try again: ");
            }
        }
    }
}