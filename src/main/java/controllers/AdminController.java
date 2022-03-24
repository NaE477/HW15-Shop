package controllers;

import entities.products.Product;
import entities.users.Admin;
import entities.users.Notification;
import repositories.impls.AdminRepositoryImpl;
import repositories.impls.NotificationRepositoryImpl;
import repositories.impls.ProductRepositoryImpl;
import services.impls.AdminServiceImpl;
import services.impls.NotificationServiceImpl;
import services.impls.ProductServiceImpl;
import services.interfaces.AdminService;
import services.interfaces.NotificationService;
import services.interfaces.ProductService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final AdminService adminService;
    private final ProductService productService;
    private final NotificationService notificationService;
    private final Admin admin;
    private final Scanner sc;
    private final Utilities utils;

    public AdminController(EntityManagerFactory factory,Integer adminId) {
        adminService = new AdminServiceImpl(new AdminRepositoryImpl(factory,Admin.class));
        productService = new ProductServiceImpl(new ProductRepositoryImpl(factory, Product.class));
        notificationService = new NotificationServiceImpl(new NotificationRepositoryImpl(factory, Notification.class));
        admin = adminService.findById(adminId);
        sc = new Scanner(System.in);
        utils = new Utilities();
    }

    public void entry() {
        label:
        while (true) {
            System.out.println("Welcome to admin section.choose an option:");
            System.out.println("1-Add Product");
            System.out.println("2-Charge Product");
            System.out.println("3-Edit Product");
            System.out.println("4-Add Admin");
            System.out.println("0-Exit");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    chargeProduct();
                    break;
                case "3":
                    editProduct();
                    break;
                case "4":
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
        Integer inStock = utils.intReceiver();
        Product toAdd = new Product(); toAdd.setProductName(productName); toAdd.setDescription(description); toAdd.setInStock(inStock);
        Product toInsert = productService.insert(toAdd);
        if (toInsert != null) System.out.println("Product Added successfully");
        else System.out.println("Something went Wrong with the database.");
    }

    private void chargeProduct() {
        while (true) {
            List<Product> products = productService.findAll();
            utils.iterateThrough(products);
            System.out.println("Enter Product ID to charge: ");
            Integer productId = utils.intReceiver();
            Product toCharge = productService.findById(productId);
            if (toCharge != null) System.out.println(toCharge);
            else break;
            System.out.println("Quantity to charge: ");
            Integer chargeQty = utils.intReceiver();

        }
    }

    private void editProduct() {

    }

    private void addAdmin() {

    }
}
