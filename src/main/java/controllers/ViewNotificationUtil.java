package controllers;

import entities.users.Customer;
import entities.users.Notification;
import repositories.impls.CustomerRepositoryImpl;
import repositories.impls.NotificationRepositoryImpl;
import services.impls.CustomerServiceImpl;
import services.impls.NotificationServiceImpl;
import services.interfaces.CustomerService;
import services.interfaces.NotificationService;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ViewNotificationUtil {
    private final NotificationService notificationService;
    private final Customer customer;

    public ViewNotificationUtil(EntityManagerFactory factory,Integer customerId) {
        notificationService = new NotificationServiceImpl(new NotificationRepositoryImpl(factory, Notification.class));
        CustomerService customerService = new CustomerServiceImpl(new CustomerRepositoryImpl(factory,Customer.class));
        customer = customerService.findById(customerId);
    }

    public void unreadNotifications() {
        List<Notification> notifications = notificationService.findByCustomer(customer);
        notifications.stream().filter(notification -> !notification.getIsRead()).forEach(System.out::println);
        notifications.stream().filter(notification -> !notification.getIsRead()).forEach(notification -> notification.setIsRead(true));
        notifications.stream().filter(Notification::getIsRead).forEach(notificationService::update);
    }

    public void allNotifications() {
        List<Notification> notifications = notificationService.findByCustomer(customer);
        notifications.forEach(System.out::println);
        notifications.stream().filter(notification -> !notification.getIsRead()).forEach(notification -> notification.setIsRead(true));
        notifications.stream().filter(Notification::getIsRead).forEach(notificationService::update);
    }
}
