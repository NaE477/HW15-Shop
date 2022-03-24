package services.impls;

import entities.users.Customer;
import entities.users.Notification;
import repositories.interfaces.NotificationRepository;
import services.interfaces.NotificationService;

import java.util.List;

public class NotificationServiceImpl extends BaseServiceImpl<Notification, NotificationRepository> implements NotificationService {
    public NotificationServiceImpl(NotificationRepository repository) {
        super(repository);
    }

    @Override
    public List<Notification> findByCustomer(Customer customer) {
        return repository.readAllByCustomer(customer);
    }
}
