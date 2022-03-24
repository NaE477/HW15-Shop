package controllers;

import entities.products.OrderedProduct;
import entities.products.Product;
import entities.users.Notification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import repositories.impls.NotificationRepositoryImpl;
import repositories.impls.OrderedProductRepositoryImpl;
import services.impls.NotificationServiceImpl;
import services.impls.OrderedProductServiceImpl;
import services.interfaces.NotificationService;
import services.interfaces.OrderedProductService;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Setter @Getter
public class SendNotificationUtil {
    private final EntityManagerFactory factory;
    private Product product;
    private Boolean nameChanged;
    private Boolean descriptionChanged;
    private Boolean isCharged;

    public void sendNotifications() {
        OrderedProductService orderedProductService = new OrderedProductServiceImpl(new OrderedProductRepositoryImpl(factory,OrderedProduct.class));
        List<OrderedProduct> orderedProducts = orderedProductService.findAllByProduct(product);
        List<Notification> notifications = new ArrayList<>();
        orderedProducts.forEach(o -> notifications.add(new Notification(o.getCustomer(),product,message(),false)));
        NotificationService notificationService = new NotificationServiceImpl(new NotificationRepositoryImpl(factory,Notification.class));
        notifications.forEach(notificationService::insert);
    }

    private String message() {
        StringBuilder message = new StringBuilder("New Changes to Product: " + product);
        if (nameChanged) message.append("\nNew Name: ").append(product.getProductName());
        if (descriptionChanged) message.append("\nNew Description: ").append(product.getDescription());
        if (isCharged) message.append("\nIs charged! In Stock: ").append(product.getInStock());
        return message.toString();
    }
}
