package repositories.impls;

import entities.users.Notification;
import repositories.interfaces.NotificationRepository;

import javax.persistence.EntityManagerFactory;

public class NotificationRepositoryImpl extends BaseRepositoryImpl<Notification> implements NotificationRepository {
    public NotificationRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Notification> clazz) {
        super(entityManagerFactory, clazz);
    }
}
