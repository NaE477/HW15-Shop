package controllers;

import entities.products.OrderedProduct;
import entities.products.Product;
import entities.users.Customer;
import entities.users.Notification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import repositories.impls.NotificationRepositoryImpl;
import repositories.impls.OrderedProductRepositoryImpl;
import repositories.impls.ProductRepositoryImpl;
import services.impls.NotificationServiceImpl;
import services.impls.OrderedProductServiceImpl;
import services.impls.ProductServiceImpl;
import services.interfaces.NotificationService;
import services.interfaces.OrderedProductService;
import services.interfaces.ProductService;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
public class SendNotificationUtil {
    private final EntityManagerFactory factory;
    private Product product;
    private Boolean nameChanged;
    private Boolean descriptionChanged;
    private Boolean isCharged;

    public SendNotificationUtil(EntityManagerFactory factory,Integer productId) {
        this.factory = factory;
        ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(factory,Product.class));
        this.product = productService.findById(productId);

        nameChanged = false; descriptionChanged = false; isCharged = false;
    }

    public void sendNotifications() {
        List<Customer> preBuyers = findPreBuyers(product);
        List<Notification> notifications = new ArrayList<>();
        preBuyers.forEach(preBuyer -> notifications.add(new Notification(preBuyer,product,message(),false)));
        NotificationService notificationService = new NotificationServiceImpl(new NotificationRepositoryImpl(factory,Notification.class));
        notifications.forEach(notificationService::insert);
    }

    private String message() {
        StringBuilder message = new StringBuilder("New Changes to Product: " + product);
        if (nameChanged) message.append("\nNew Name: ").append(product.getProductName());
        if (descriptionChanged) message.append("\nNew Description: ").append(product.getDescription());
        if (isCharged) message.append("\nIt is charged! In Stock: ").append(product.getInStock());
        return message.toString();
    }

    private List<Customer> findPreBuyers(Product product) {
        OrderedProductService orderedProductService = new OrderedProductServiceImpl(new OrderedProductRepositoryImpl(factory,OrderedProduct.class));
        return orderedProductService
                .findAllByProduct(product)
                .stream()
                .filter(order -> !order.getIsFinalized())
                .map(OrderedProduct::getCustomer)
                .collect(Collectors.toList());
    }
}
