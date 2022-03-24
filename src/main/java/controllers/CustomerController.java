package controllers;

import entities.products.OrderedProduct;
import entities.products.Product;
import entities.users.Customer;
import repositories.impls.CustomerRepositoryImpl;
import repositories.impls.OrderedProductRepositoryImpl;
import repositories.impls.ProductRepositoryImpl;
import services.impls.CustomerServiceImpl;
import services.impls.OrderedProductServiceImpl;
import services.impls.ProductServiceImpl;
import services.interfaces.CustomerService;
import services.interfaces.OrderedProductService;
import services.interfaces.ProductService;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerController {
    private final ProductService productService;
    private final ViewNotificationUtil notificationUtil;
    private final OrderedProductService orderedProductService;
    private final Customer customer;
    private final Scanner sc;
    private final Utilities utils;

    public CustomerController(EntityManagerFactory factory, Integer customerId) {
        CustomerService customerService = new CustomerServiceImpl(new CustomerRepositoryImpl(factory, Customer.class));
        productService = new ProductServiceImpl(new ProductRepositoryImpl(factory, Product.class));
        notificationUtil = new ViewNotificationUtil(factory,customerId);
        orderedProductService = new OrderedProductServiceImpl(new OrderedProductRepositoryImpl(factory, OrderedProduct.class));
        customer = customerService.findById(customerId);
        sc = new Scanner(System.in);
        utils = new Utilities();
    }

    public void entry() {
        label:
        while (true) {
            System.out.println("1-Buy Product");
            System.out.println("2-Unread Notifications");
            System.out.println("2-All Notifications");
            System.out.println("0-Exit");
            switch (sc.nextLine()) {
                case "1":
                    buyProduct();
                    break;
                case "2":
                    notifications();
                    break;
                case "3":
                    allNotifications();
                    break;
                case "0":
                    break label;
            }
        }
    }

    private void buyProduct() {
        List<Product> products = productService.findAll();
        utils.iterateThrough(products);
        System.out.println("Enter product ID to add ");
        Integer productId = utils.intReceiver();
        Product product = products.stream().filter(p -> Objects.equals(p.getId(), productId)).findAny().orElse(null);
        if (product == null) System.out.println("Wrong ID");
        else {
            System.out.println(product);
            System.out.println("Quantity: ");
            Integer quantity = utils.positiveIntReceiver();
            if (quantity > product.getInStock()) System.out.println("Can't order bigger than stock");
            else {
                var order = orderedProductService.insert(new OrderedProduct(customer,product,quantity,false));
                if (order != null) System.out.println("Order saved successfully");
                else System.out.println("Something went wrong with the database");
            }
        }
    }

    private void notifications() {
        notificationUtil.unreadNotifications();
    }

    private void allNotifications() {
        notificationUtil.allNotifications();
    }
}
