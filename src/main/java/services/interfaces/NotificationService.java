package services.interfaces;

import entities.users.Customer;
import entities.users.Notification;

import java.util.List;

public interface NotificationService extends BaseService<Notification> {
    List<Notification> findByCustomer(Customer customer);
}
