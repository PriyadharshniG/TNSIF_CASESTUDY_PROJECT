package com.tnsif.OnlineShopping.application;

import java.util.Scanner;
import com.tnsif.OnlineShopping.entities.Product;
import com.tnsif.OnlineShopping.services.ProductService;
import com.tnsif.OnlineShopping.services.CustomerService;
import com.tnsif.OnlineShopping.services.OrderService;
import com.tnsif.OnlineShopping.services.AdminService;

public class OnlineShopping {

    private static ProductService productService = new ProductService();
    private static CustomerService customerService = new CustomerService();
    private static OrderService orderService = new OrderService();
    private static AdminService adminService = new AdminService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Online Shopping System ===");
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminMenu(scanner);
                    break;

                case 2:
                    customerMenu(scanner);
                    break;

                case 3:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // ================== ADMIN MENU ==================
    private static void adminMenu(Scanner scanner) {
        int adminChoice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            System.out.print("Choose an option: ");
            adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1 -> addProduct(scanner);
                case 2 -> removeProduct(scanner);
                case 3 -> viewProducts();
                case 4 -> createAdmin(scanner);
                case 5 -> viewAdmins();
                case 6 -> updateOrderStatus(scanner);
                case 7 -> viewOrders();
                case 8 -> System.out.println("Exiting Admin Menu...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (adminChoice != 8);
    }

    // ================== CUSTOMER MENU ==================
    private static void customerMenu(Scanner scanner) {
        int customerChoice;
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            customerChoice = scanner.nextInt();

            switch (customerChoice) {
                case 1 -> createCustomer(scanner);
                case 2 -> viewCustomers();
                case 3 -> placeOrder(scanner);
                case 4 -> viewOrders();
                case 5 -> viewProducts();
                case 6 -> System.out.println("Exiting Customer Menu...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (customerChoice != 6);
    }

    // ================== ADMIN METHODS ==================
    private static void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        int stockQuantity = scanner.nextInt();

        Product product = new Product(productId, name, price, stockQuantity);
        productService.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void removeProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        productService.removeProduct(productId);
        System.out.println("Product removed successfully!");
    }

    private static void viewProducts() {
        productService.viewProducts();
    }

    private static void createAdmin(Scanner scanner) {
        adminService.createAdmin(scanner);
    }

    private static void viewAdmins() {
        adminService.viewAdmins();
    }

    private static void updateOrderStatus(Scanner scanner) {
        adminService.updateOrderStatus(scanner, orderService);
    }

    private static void viewOrders() {
        orderService.getOrders().forEach(order ->
            System.out.println("Order ID: " + order.getOrderId() + ", Status: " + order.getStatus())
        );
    }

    // ================== CUSTOMER METHODS ==================
    private static void createCustomer(Scanner scanner) {
        customerService.createCustomer(scanner);
    }
