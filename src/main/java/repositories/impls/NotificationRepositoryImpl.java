package repositories.impls;

import entities.users.Customer;
import entities.users.Notification;
import repositories.interfaces.NotificationRepository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class NotificationRepositoryImpl extends BaseRepositoryImpl<Notification> implements NotificationRepository {
    public NotificationRepositoryImpl(EntityManagerFactory entityManagerFactory, Class<Notification> clazz) {
        super(entityManagerFactory, clazz);
    }

    @Override
    public List<Notification> readAllByCustomer(Customer customer) {
        return super.getEntityManagerFactory().createEntityManager()
                .createQuery("select n from Notification n where n.customer.id = :customerId",getClazz())
                .setParameter("customerId",customer.getId())
                .getResultList();
    }
}
