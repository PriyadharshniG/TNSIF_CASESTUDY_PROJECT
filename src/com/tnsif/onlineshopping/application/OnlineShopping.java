package com.tnsif.onlineshopping.application;
import com.tnsif.onlineshopping.services.*;
import com.tnsif.onlineshopping.entities.*;
import java.util.Scanner;

/**
 * Menu-driven driver class for Admin and Customer modules.
 * Matches the flows shown in the case study PDF.
 */
public class OnlineShopping {
    private static final ProductService productService = new ProductService();
    private static final AdminService adminService = new AdminService();
    private static final CustomerService customerService = new CustomerService();
    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {
        seedSampleData();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit\n");
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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        sc.close();
    }

    private static void seedSampleData() {
        productService.addProduct(new Product(101, "T-Shirt", 560.0, 100));
        productService.addProduct(new Product(102, "Trouser", 1400.0, 50));
    }

    private static void adminMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\nAdmin Menu:\n1. Add Product\n2. Remove Product\n3. View Products\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
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
                    System.out.println("Product added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int rid = readInt(sc);
                    if (productService.removeProduct(rid)) System.out.println("Product removed.");
                    else System.out.println("Product not found.");
                    break;
                case 3:
                    System.out.println("Products:\n");
                    for (Product p : productService.getAllProducts()) System.out.println(p);
                    break;
                case 4:
                    System.out.print("Enter Admin ID: ");
                    int aid = readInt(sc);
                    System.out.print("Enter Admin Username: ");
                    String aname = sc.nextLine();
                    System.out.print("Enter Admin Email: ");
                    String aemail = sc.nextLine();
                    adminService.addAdmin(new Admin(aid, aname, aemail));
                    System.out.println("Admin created successfully!");
                    break;
                case 5:
                    System.out.println("Admins:");
                    for (Admin a : adminService.getAdmins()) System.out.println(a);
                    break;
                case 6:
                    System.out.print("Enter Order ID: ");
                    int oid = readInt(sc);
                    Order o = orderService.findById(oid);
                    if (o == null) {
                        System.out.println("Order not found.");
                        break;
                    }
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String st = sc.nextLine();
                    o.setStatus(st);
                    System.out.println("Order updated.");
                    break;
                case 7:
                    System.out.println("Orders:\n");
                    for (Order order : orderService.getAllOrders()) System.out.println(order);
                    break;
                case 8:
                    back = true;
                    System.out.println("Exiting Admin...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void customerMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\nCustomer Menu:\n1. Create Customer\n2. View Customers\n3. Place Order\n4. View Orders\n5. View Products\n6. Return");
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
                    System.out.println("Customer created successfully!");
                    break;
                case 2:
                    System.out.println("Customers:\n");
                    for (Customer c : customerService.getAllCustomers()) System.out.println(c);
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int cid = readInt(sc);
                    Customer cust = customerService.findById(cid);
                    if (cust == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    Order order = new Order(cust);
                    while (true) {
                        System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                        int pid = readInt(sc);
                        if (pid == -1) break;
                        Product p = productService.findById(pid);
                        if (p == null) {
                            System.out.println("Product not found.");
                            continue;
                        }
                        System.out.print("Enter quantity: ");
                        int q = readInt(sc);
                        if (q <= 0) {
                            System.out.println("Invalid qty");
                            continue;
                        }
                        if (p.getStockQuantity() < q) {
                            System.out.println("Not enough stock. Available: " + p.getStockQuantity());
                            continue;
                        }
                        // reduce stock and add to order
                        p.reduceStock(q);
                        order.addProduct(p, q);
                    }
                    orderService.addOrder(order);
                    cust.addOrder(order);
                    System.out.println("Order placed successfully!");
                    break;
                case 4:
                    System.out.print("Enter Customer ID: ");
                    int ccid = readInt(sc);
                    Customer customer = customerService.findById(ccid);
                    if (customer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }
                    System.out.println("Orders:\n");
                    for (Order o : customer.getOrders()) System.out.println(o);
                    break;
                case 5:
                    System.out.println("Products:\n");
                    for (Product p : productService.getAllProducts()) System.out.println(p);
                    break;
                case 6:
                    back = true;
                    System.out.println("Exiting Customer Menu...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // utility methods to read ints and doubles robustly
    private static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid integer. Please enter again: ");
            }
        }
    }

    private static double readDouble(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter again: ");
            }
        }
    }
}