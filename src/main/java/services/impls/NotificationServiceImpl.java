package services.impls;

import entities.users.Notification;
import repositories.interfaces.NotificationRepository;
import services.interfaces.NotificationService;

public class NotificationServiceImpl extends BaseServiceImpl<Notification, NotificationRepository> implements NotificationService {
    public NotificationServiceImpl(NotificationRepository repository) {
        super(repository);
    }
}
