package repositories.interfaces;

import entities.users.Customer;
import entities.users.Notification;

import java.util.List;

public interface NotificationRepository extends BaseRepository<Notification> {
    List<Notification> readAllByCustomer(Customer customer);
}
