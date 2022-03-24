package controllers;

import entities.products.OrderedProduct;
import entities.products.Product;
import entities.users.Customer;
import entities.users.Notification;
import repositories.impls.CustomerRepositoryImpl;
import repositories.impls.NotificationRepositoryImpl;
import repositories.impls.OrderedProductRepositoryImpl;
import repositories.impls.ProductRepositoryImpl;
import services.impls.CustomerServiceImpl;
import services.impls.NotificationServiceImpl;
import services.impls.OrderedProductServiceImpl;
import services.impls.ProductServiceImpl;
import services.interfaces.CustomerService;
import services.interfaces.NotificationService;
import services.interfaces.OrderedProductService;
import services.interfaces.ProductService;

import javax.persistence.EntityManagerFactory;

public class CustomerController {
    private final CustomerService customerService;
    private final ProductService productService;
    private final NotificationService notificationService;
    private final OrderedProductService orderedProductService;
    private final Customer customer;

    public CustomerController(EntityManagerFactory factory,Integer customerId) {
        customerService = new CustomerServiceImpl(new CustomerRepositoryImpl(factory,Customer.class));
        productService = new ProductServiceImpl(new ProductRepositoryImpl(factory, Product.class));
        notificationService = new NotificationServiceImpl(new NotificationRepositoryImpl(factory, Notification.class));
        orderedProductService = new OrderedProductServiceImpl(new OrderedProductRepositoryImpl(factory, OrderedProduct.class));
        customer = customerService.findById(customerId);
    }
}
