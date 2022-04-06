package controllers;

import entities.products.Product;
import entities.users.Admin;
import entities.users.Customer;
import repositories.impls.AdminRepositoryImpl;
import repositories.impls.CustomerRepositoryImpl;
import repositories.impls.ProductRepositoryImpl;
import services.impls.AdminServiceImpl;
import services.impls.CustomerServiceImpl;
import services.impls.ProductServiceImpl;
import services.interfaces.AdminService;
import services.interfaces.CustomerService;
import services.interfaces.ProductService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final AdminService adminService;
    private final ProductService productService;
    private final EntityManagerFactory factory;
    private final Scanner sc;
    private final Utilities utils;

    public AdminController(EntityManagerFactory factory, Integer adminId) {
        adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory, Admin.class));
        productService = new ProductServiceImpl(new ProductRepositoryImpl(factory, Product.class));
        this.factory = factory;
        Admin admin = adminService.findById(adminId);
        sc = new Scanner(System.in);
        utils = new Utilities();
    }

    public void entry() {
        label:
        while (true) {
            System.out.println("Welcome to admin section.choose an option:");
            System.out.println("1-Add Product");
            System.out.println("2-Edit Product");
            System.out.println("3-Add Admin");
            System.out.println("0-Exit");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    editProduct();
                    break;
                case "3":
                    addAdmin();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
    }

    private void addProduct() {
        System.out.println("Product Name: ");
        String productName = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();
        System.out.println("Stock: ");
        Integer inStock = positiveIntReceiver();
        Product toAdd = new Product();
        toAdd.setProductName(productName);
        toAdd.setDescription(description);
        toAdd.setInStock(inStock);
        Product toInsert = productService.insert(toAdd);
        if (toInsert != null) System.out.println("Product Added successfully");
        else System.out.println("Something went Wrong with the database.");
    }

    private void editProduct() {
        List<Product> products = productService.findAll();
        utils.iterateThrough(products);
        System.out.println("Enter Product ID to edit");
        Product toEdit = productService.findById(utils.intReceiver());
        if (toEdit == null) System.out.println("Wrong ID");
        else {
            label:
            while (true) {
                SendNotificationUtil notificationUtil = new SendNotificationUtil(factory, toEdit.getId());
                System.out.println("1-Edit Name");
                System.out.println("2-Edit Description");
                System.out.println("3-Charge");
                System.out.println("0-Exit");
                String opt = sc.nextLine();
                switch (opt) {
                    case "1":
                        System.out.println("New Name: ");
                        String newName = sc.nextLine();
                        toEdit.setProductName(newName);
                        notificationUtil.setNameChanged(true);
                        break;
                    case "2":
                        System.out.println("New Description: ");
                        String newDescription = sc.nextLine();
                        toEdit.setDescription(newDescription);
                        notificationUtil.setDescriptionChanged(true);
                        break;
                    case "3":
                        System.out.println("Charge Quantity: ");
                        Integer chargeQty = positiveIntReceiver();
                        toEdit.setInStock(toEdit.getInStock() + chargeQty);
                        notificationUtil.setIsCharged(true);
                        break;
                    case "0":
                        if (notificationUtil.getDescriptionChanged()
                                || notificationUtil.getIsCharged()
                                || notificationUtil.getNameChanged()) {
                            notificationUtil.sendNotifications();
                            productService.update(toEdit);
                        }
                        break label;
                }
            }
        }
    }

    private void addAdmin() {
        System.out.println("Username: ");
        String username = usernameReceiver();
        System.out.println("Password: ");
        String password = sc.nextLine();
        Admin toIns = adminService.insert(new Admin(username, password));
        if (toIns != null) System.out.println("Admin created");
        else System.out.println("Something went wrong with the database.");
    }

    private Integer positiveIntReceiver() {
        while (true) {
            int output = utils.intReceiver();
            if (output > 0) return output;
            else System.out.println("Only Positive numbers here.");
        }
    }

    private String usernameReceiver() {
        CustomerService customerService = new CustomerServiceImpl(new CustomerRepositoryImpl(factory, Customer.class));
        while (true) {
            String username = sc.nextLine();
            if (adminService.findByUsername(username) == null && customerService.findByUsername(username) == null)
                return username;
            else System.out.println("Username already exists");
        }
    }
}
